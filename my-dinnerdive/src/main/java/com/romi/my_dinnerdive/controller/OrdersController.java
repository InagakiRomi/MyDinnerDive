package com.romi.my_dinnerdive.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.romi.my_dinnerdive.dto.CreateOrderRequest;
import com.romi.my_dinnerdive.service.OrdersService;

import jakarta.validation.Valid;

/** 提供與「購物車」相關的 RESTful API */
public class OrdersController {
    
    @Autowired
    private OrdersService ordersService;

    @PostMapping("/users/{userId}/orders")
    public ResponseEntity<?> createOrder(@PathVariable Integer userId,
                                         @RequestBody @Valid CreateOrderRequest createOrderRequest){
        Integer orderId = ordersService.createOrder(userId, createOrderRequest);

        //Orders order = ordersService.getOrderById(orderId);

        return ResponseEntity.status(HttpStatus.CREATED).body(orderId);

        //return ResponseEntity.status(HttpStatus.CREATED).body(order);
    }
}
