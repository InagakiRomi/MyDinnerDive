package com.romi.my_dinnerdive.service;

import java.util.List;

import com.romi.my_dinnerdive.dto.DishRequest;
import com.romi.my_dinnerdive.model.Dish;

/** 餐廳服務介面，定義與餐點相關的商業邏輯操作 */
public interface DishService {

    /** 查詢指定餐點單筆資料 */
    Dish getDishById(Integer dishId);

    /** 查詢指定餐廳餐點資料 */
    List<Dish> findByRestaurantId(Integer restaurantId);

    /** 新增餐點 */
    Integer createDish(DishRequest dishRequest);

    /** 刪除指定餐點 */
    void deleteDishById(Integer dishId);
}
