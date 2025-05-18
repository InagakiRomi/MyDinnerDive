package com.romi.my_dinnerdive.dao.Impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import com.romi.my_dinnerdive.dao.RestaurantDao;
import com.romi.my_dinnerdive.model.Restaurant;
import com.romi.my_dinnerdive.rowmapper.RestaurantRowMapper;

@Component
public class RestaurantDaoImpl implements RestaurantDao{
    
    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Override
    public Restaurant getRestaurantById(Integer restaurantId){
        String sql = "SELECT restaurant_id, restaurant_name, category, image_url, visited_count, last_visited_at, note " +
                "FROM restaurants WHERE restaurant_id = :restaurantId";

        Map<String, Object> map = new HashMap<>();
        map.put("restaurantId", restaurantId);

        List<Restaurant> restaurantList = namedParameterJdbcTemplate.query(sql, map, new RestaurantRowMapper());

        if(restaurantList.size() > 0){
            return restaurantList.get(0);
        }else{
            return null;
        }
    }
}
