package com.greatlearning.microservice.repository;

import com.greatlearning.microservice.entity.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface OrdersRepository extends JpaRepository<Orders, Long> {

    List<Orders> findOrdersByOrderDate(Date date);

    List<Orders> findOrdersByAmount(double amount);

}
