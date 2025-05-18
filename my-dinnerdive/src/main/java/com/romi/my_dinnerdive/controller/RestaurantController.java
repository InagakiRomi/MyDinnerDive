package com.romi.my_dinnerdive.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.romi.my_dinnerdive.model.Restaurant;
import com.romi.my_dinnerdive.service.RestaurantService;

@RestController
public class RestaurantController {

    @Autowired
    private RestaurantService restaurantService;

    @GetMapping("/restaurants/{restaurantId}")
    public ResponseEntity<Restaurant> getRestaurant(@PathVariable Integer restaurantId){
        Restaurant restaurant = restaurantService.getRestaurantById(restaurantId);

        if(restaurant != null){
            return ResponseEntity.status(HttpStatus.OK).body(restaurant);
        } else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
    }    
}
