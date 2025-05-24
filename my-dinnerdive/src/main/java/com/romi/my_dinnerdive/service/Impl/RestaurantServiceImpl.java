package com.romi.my_dinnerdive.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.romi.my_dinnerdive.dao.RestaurantDao;
import com.romi.my_dinnerdive.dto.RestaurantRequest;
import com.romi.my_dinnerdive.model.Restaurant;
import com.romi.my_dinnerdive.service.RestaurantService;

/**
 * 餐廳服務的實作類別，實作商業邏輯與資料處理流程。
 */
@Service
public class RestaurantServiceImpl implements RestaurantService {

    @Autowired
    private RestaurantDao restaurantDao;

    /**
     * 根據餐廳 ID 查詢資料。
     */
    @Override
    public Restaurant getRestaurantById(Integer restaurantId) {
        return restaurantDao.getRestaurantById(restaurantId);
    }

    /**
     * 建立新的餐廳資料。
     */
    @Override
    public Integer createRestaurant(RestaurantRequest restaurantRequest) {
        return restaurantDao.createRestaurant(restaurantRequest);
    }

    /**
     * 隨機取得一筆餐廳資料。
     */
    @Override
    public Restaurant getRandomRestaurant() {
        return restaurantDao.findRandomRestaurant();
    }
}