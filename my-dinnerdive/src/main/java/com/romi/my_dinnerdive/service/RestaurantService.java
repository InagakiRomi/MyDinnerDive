package com.romi.my_dinnerdive.service;

import com.romi.my_dinnerdive.dto.RestaurantRequest;
import com.romi.my_dinnerdive.model.Restaurant;

/**
 * 服務層介面：RestaurantService
 *
 * 定義與餐廳資料操作相關的商業邏輯，負責橋接 Controller 與 DAO 層級，
 * 提供查詢與新增餐廳的功能。
 */
public interface RestaurantService {

    /**
     * 根據餐廳 ID 查詢餐廳資料。
     *
     * @param restaurantId 餐廳的唯一識別碼
     * @return 查詢到的 {@link Restaurant} 資料；若無資料則回傳 null
     */
    Restaurant getRestaurantById(Integer restaurantId);

    /**
     * 建立一筆新的餐廳資料。
     *
     * @param restaurantRequest 包含新餐廳資訊的請求物件
     * @return 新建立的餐廳主鍵 ID
     */
    Integer createRestaurant(RestaurantRequest restaurantRequest);
}