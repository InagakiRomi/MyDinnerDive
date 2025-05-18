package com.romi.my_dinnerdive.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.romi.my_dinnerdive.dao.RestaurantDao;
import com.romi.my_dinnerdive.model.Restaurant;
import com.romi.my_dinnerdive.service.RestaurantService;

@Component
public class RestaurantServiceImpl implements RestaurantService{

    @Autowired
    private RestaurantDao restaurantDao;

    @Override
    public Restaurant getRestaurantById(Integer restaurantId) {
        return restaurantDao.getRestaurantById(restaurantId);
    }
    
}
