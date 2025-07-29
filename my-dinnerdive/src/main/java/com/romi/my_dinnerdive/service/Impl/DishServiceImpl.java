package com.romi.my_dinnerdive.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.romi.my_dinnerdive.dao.DishDao;
import com.romi.my_dinnerdive.model.Dish;
import com.romi.my_dinnerdive.service.DishService;

@Service
public class DishServiceImpl implements DishService{
    @Autowired
    private DishDao dishDao;

    @Override
    public List<Dish> findByRestaurantId(Integer restaurantId) {
        return dishDao.findByRestaurantId(restaurantId);
    }
}
