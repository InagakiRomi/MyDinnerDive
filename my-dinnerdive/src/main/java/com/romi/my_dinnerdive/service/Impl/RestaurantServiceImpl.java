package com.romi.my_dinnerdive.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.romi.my_dinnerdive.dao.RestaurantDao;
import com.romi.my_dinnerdive.dto.RestaurantRequest;
import com.romi.my_dinnerdive.model.Restaurant;
import com.romi.my_dinnerdive.service.RestaurantService;

/**
 * {@link RestaurantService} 的實作類別。
 *
 * 負責處理與餐廳資料相關的業務邏輯，並調用 {@link RestaurantDao} 執行實際的資料存取操作。
 */
@Component
public class RestaurantServiceImpl implements RestaurantService {

    /**
     * 注入 DAO 物件，用於資料層操作。
     */
    @Autowired
    private RestaurantDao restaurantDao;

    /**
     * 根據餐廳 ID 查詢餐廳資料。
     *
     * @param restaurantId 餐廳的唯一識別碼
     * @return 對應的 {@link Restaurant} 物件，若無資料則回傳 null
     */
    @Override
    public Restaurant getRestaurantById(Integer restaurantId) {
        return restaurantDao.getRestaurantById(restaurantId);
    }

    /**
     * 建立一筆新的餐廳資料。
     *
     * @param restaurantRequest 請求建立的餐廳資訊
     * @return 新建立餐廳的主鍵 ID
     */
    @Override
    public Integer createRestaurant(RestaurantRequest restaurantRequest) {
        return restaurantDao.createRestaurant(restaurantRequest);
    }
}