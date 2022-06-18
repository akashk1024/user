package com.greatlearning.microservice.controller;

import com.greatlearning.microservice.service.MenuItemServiceImpl;
import com.greatlearning.microservice.entity.MenuItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/app")
public class MenuItemController {

    @Autowired
    private MenuItemServiceImpl itemService;

    @GetMapping("/itemList")
    public ResponseEntity<List<MenuItem>> findAll() {
        List<MenuItem> menuItems = itemService.findAll();
        return new ResponseEntity<>(menuItems, HttpStatus.OK);
    }

    @GetMapping("/findByIdemId")
    public ResponseEntity<MenuItem> findByUserName(@RequestParam Integer itemId) {
        try {
            MenuItem menuItem = itemService.findByItemId(itemId);
            if(menuItem == null){
                throw new Exception("Not present");
            }
            return new ResponseEntity<>(menuItem, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }

    }


}
