package com.romi.my_dinnerdive.dao;

import java.util.List;

import com.romi.my_dinnerdive.dto.RestaurantRequest;
import com.romi.my_dinnerdive.model.Restaurant;

/**
 * 餐廳 DAO 介面，定義與資料庫互動的方法。
 */
public interface RestaurantDao {

    /**
     * 根據 ID 查詢餐廳。
     *
     * @param restaurantId 餐廳 ID
     * @return 對應的餐廳資料，若無資料則為 null
     */
    Restaurant getRestaurantById(Integer restaurantId);

    /**
     * 建立一筆餐廳資料。
     *
     * @param restaurantRequest 新增餐廳請求物件
     * @return 資料庫產生的餐廳 ID
     */
    Integer createRestaurant(RestaurantRequest restaurantRequest);

    /**
     * 取得所有餐廳資料ID。
     *
     * @return 所有餐廳資料ID，若無資料則回傳 null
     */
    List<Integer> getAllRestaurantIds();
}