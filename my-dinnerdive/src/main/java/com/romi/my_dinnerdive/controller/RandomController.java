package com.romi.my_dinnerdive.controller;

import com.romi.my_dinnerdive.model.Restaurant;
import com.romi.my_dinnerdive.service.RestaurantService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * 抽籤控制器：專責處理隨機抽選餐廳的邏輯。
 */
@RestController
public class RandomController {

    @Autowired
    private RestaurantService restaurantService;

    /**
     * 抽選一筆隨機餐廳（未來可給 AJAX 使用）
     */
    @GetMapping("/random")
    public Restaurant getRandomRestaurant() {
        return restaurantService.getRandomRestaurant();
    }
}