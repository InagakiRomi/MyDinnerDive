package com.romi.my_dinnerdive.dao;

import com.romi.my_dinnerdive.model.Restaurant;

public interface RestaurantDao {
    Restaurant getRestaurantById(Integer restaurantId);
}
