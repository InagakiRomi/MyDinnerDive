package com.romi.my_dinnerdive.dao;

import com.romi.my_dinnerdive.dto.RestaurantRequest;
import com.romi.my_dinnerdive.model.Restaurant;

/**
 * DAO 介面：定義與餐廳（Restaurant）資料表相關的資料存取操作。
 * 
 * 負責處理餐廳資料的查詢與新增，供服務層調用以與資料庫互動。
 */
public interface RestaurantDao {

    /**
     * 根據餐廳 ID 查詢餐廳資訊。
     *
     * @param restaurantId 餐廳的唯一識別碼
     * @return 查詢到的 {@link Restaurant} 物件；若無資料則回傳 null
     */
    Restaurant getRestaurantById(Integer restaurantId);

    /**
     * 建立一筆新的餐廳資料。
     *
     * @param restaurantRequest 包含新增餐廳所需的請求參數
     * @return 新增資料後所產生的餐廳 ID（主鍵）
     */
    Integer createRestaurant(RestaurantRequest restaurantRequest);
}