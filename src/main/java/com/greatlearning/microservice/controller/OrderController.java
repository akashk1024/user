package com.greatlearning.microservice.controller;

import com.greatlearning.microservice.dto.ItemEntryDto;
import com.greatlearning.microservice.entity.Orders;
import com.greatlearning.microservice.service.LoggedInUserService;
import com.greatlearning.microservice.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;


@RestController
@RequestMapping("/app")
@Secured("USER")
public class OrderController {

    @Autowired
    private LoggedInUserService loggedInUserService;

    @Autowired
    private OrderService orderService;

    @GetMapping("/currentUserName")
    public ResponseEntity<String> currentUserName(){
        return new ResponseEntity<>(loggedInUserService.getUserName(), HttpStatus.OK);
    }

    @PostMapping("/addItemEntry")
    public ResponseEntity<Orders> addItems(@RequestBody List<ItemEntryDto> itemEntries){
        try {
            Orders orders = orderService.save(itemEntries);
            return new ResponseEntity<>(orders, HttpStatus.ACCEPTED);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
    }


    @PutMapping("/update_order")
    public Orders updateOrder(@RequestParam Long orderId, @RequestParam int itemId, @RequestParam int quantity) {
        return orderService.updateOrder(orderId, itemId, quantity);
    }

    @GetMapping("show_bill")
    public double getOrder(@RequestParam long orderId) {
        return orderService.getByOrderId(orderId);
    }

    @GetMapping("view-all-order")
    public List<Orders> getAllOrders() {
        return orderService.getAllOrders();
    }

    @GetMapping("view-order-by-date")
    public List<Orders> getOrderByDate(@RequestParam Date date) {
        return orderService.getOrderByDate(date);
    }

    @GetMapping("view-order-by-price")
    public List<Orders> getOrderByDate(double amount) {
        return orderService.getOrderByPrice(amount);
    }

    @PostMapping("remove_order")
    public void deleteOrder(Long orderId) {
        orderService.deleteOrder(orderId);
    }

}
