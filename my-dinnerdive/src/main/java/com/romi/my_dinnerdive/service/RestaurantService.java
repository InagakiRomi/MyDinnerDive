package com.romi.my_dinnerdive.service;

import java.util.List;

import com.romi.my_dinnerdive.dto.RestaurantRequest;
import com.romi.my_dinnerdive.model.Restaurant;

/**
 * 餐廳服務介面，定義商業邏輯操作。
 * 負責連接 Controller 與 DAO 層，處理與餐廳相關的業務邏輯。
 */
public interface RestaurantService {

    /**
     * 根據餐廳 ID 查詢資料。
     *
     * @param restaurantId 餐廳的唯一識別碼
     * @return 查詢結果的 Restaurant 物件，若無資料則回傳 null
     */
    Restaurant getRestaurantById(Integer restaurantId);

    /**
     * 建立一筆新的餐廳資料。
     *
     * @param restaurantRequest 餐廳請求物件，包含新增所需資訊
     * @return 新建立資料的主鍵 ID
     */
    Integer createRestaurant(RestaurantRequest restaurantRequest);

    /**
     * 取得所有餐廳資料ID。
     *
     * @return 所有餐廳資料ID，若無資料則回傳 null
     */
    List<Integer> getAllRestaurantIds();

    /**
     * 隨機取得一筆餐廳資料。
     *
     * @return 隨機選出的 Restaurant 物件，若無資料則回傳 null
     */
    Restaurant getRandomRestaurant();

    /**
     * 清空抽籤資料。
     *
     * @return 清除後的餐廳資料列表，若無資料則回傳 null
     */
    void clearRandomRestaurant();
}