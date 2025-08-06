package com.romi.my_dinnerdive.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.romi.my_dinnerdive.dto.DishRequest;
import com.romi.my_dinnerdive.model.Dish;
import com.romi.my_dinnerdive.service.DishService;

import jakarta.validation.Valid;

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

    /** 新增一筆餐點資料 */
    @PostMapping(value = "/dishes")
    public ResponseEntity<Dish> createDish(
            // 從前端取得請求資料，並驗證格式
            @RequestBody @Valid DishRequest dishRequest
    ) {
        // 呼叫 Service 層，將新增資料寫入資料庫，取得新產生的 dishId
        Integer dishId = dishService.createDish(dishRequest);

        // 根據新產生的 ID，再查詢該筆資料
        Dish dish = dishService.getDishById(dishId);

        // 回傳 HTTP 201（Created）狀態碼，並附上新增成功的資料
        return ResponseEntity.status(HttpStatus.CREATED).body(dish);
    }

    /** 刪除指定的餐點資料 */
    @DeleteMapping("/dishes/{dishId}")
    public ResponseEntity<?> deleteDish(@PathVariable Integer dishId) {

        // 呼叫 Service 層刪除該餐點資料（若不存在也不會報錯）
        dishService.deleteDishById(dishId);

        // 回傳 HTTP 204（No Content），代表刪除成功
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
