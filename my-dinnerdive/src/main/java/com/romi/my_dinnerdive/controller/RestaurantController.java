package com.romi.my_dinnerdive.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.romi.my_dinnerdive.dto.RestaurantRequest;
import com.romi.my_dinnerdive.model.Restaurant;
import com.romi.my_dinnerdive.service.RestaurantService;

import jakarta.validation.Valid;

/**
 * 控制器類別：RestaurantController
 * 
 * 提供與餐廳相關的 RESTful API，包含查詢特定餐廳資料及新增餐廳紀錄的功能。
 */
@RestController
public class RestaurantController {

    @Autowired
    private RestaurantService restaurantService;

    /**
     * 根據餐廳 ID 查詢資料。
     *
     * @param restaurantId 餐廳的唯一識別碼
     * @return 若查詢成功，回傳對應餐廳資料與 HTTP 200；否則回傳 HTTP 404。
     */
    @GetMapping("/restaurants/{restaurantId}")
    public ResponseEntity<Restaurant> getRestaurant(@PathVariable Integer restaurantId){
        Restaurant restaurant = restaurantService.getRestaurantById(restaurantId);

        if(restaurant != null){
            return ResponseEntity.status(HttpStatus.OK).body(restaurant);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    /**
     * 建立新的餐廳資料。
     *
     * @param restaurantRequest 新增餐廳所需的請求資料，需通過驗證
     * @return 回傳建立後的餐廳資料與 HTTP 201 狀態碼
     */
    @PostMapping("/restaurants")
    public ResponseEntity<Restaurant> createRestaurant(@RequestBody @Valid RestaurantRequest restaurantRequest,
                                                       @RequestParam(defaultValue = "0") Integer visitedCount) {
        Integer restaurantId = restaurantService.createRestaurant(restaurantRequest);
        Restaurant restaurant = restaurantService.getRestaurantById(restaurantId);
        return ResponseEntity.status(HttpStatus.CREATED).body(restaurant);
    }
}