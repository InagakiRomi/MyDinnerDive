package com.romi.my_dinnerdive.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.romi.my_dinnerdive.model.Dish;
import com.romi.my_dinnerdive.service.DishService;

/** 提供與「餐廳餐點」相關的 RESTful API，包含餐點的查詢等功能 */
@RestController
public class DishController {

    @Autowired
    private DishService dishService;

    /** 根據餐廳 ID 查詢餐點資料 */
    @GetMapping("/restaurants/{restaurantId}/dishes")
    public ResponseEntity<List<Dish>> getDishes(@PathVariable Integer restaurantId){
        List<Dish> dish = dishService.findByRestaurantId(restaurantId);

        return ResponseEntity.status(HttpStatus.OK).body(dish);
    }
}
