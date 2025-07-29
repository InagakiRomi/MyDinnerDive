package com.romi.my_dinnerdive.dao.Impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.romi.my_dinnerdive.dao.DishDao;
import com.romi.my_dinnerdive.model.Dish;
import com.romi.my_dinnerdive.rowmapper.DishRowmapper;

@Repository
public class DishDaoImpl implements DishDao {
    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Override
    public List<Dish> findByRestaurantId(Integer restaurantId) {
        String sql = "SELECT dish_id, restaurant_id, price, dish_name " +
                     "FROM dishes WHERE restaurant_id = :restaurantId";

        Map<String, Object> map = new HashMap<>();
        map.put("restaurantId", restaurantId);

        List<Dish> dishList = namedParameterJdbcTemplate.query(sql, map, new DishRowmapper());

        return dishList;
    }
}
