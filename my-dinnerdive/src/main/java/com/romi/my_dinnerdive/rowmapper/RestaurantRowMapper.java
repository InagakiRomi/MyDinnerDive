package com.romi.my_dinnerdive.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.lang.NonNull;

import com.romi.my_dinnerdive.model.Restaurant;

public class RestaurantRowMapper implements RowMapper<Restaurant>{

    @Override
    public Restaurant mapRow(@NonNull ResultSet resultSet, int i) throws SQLException {
        Restaurant restaurant = new Restaurant();

        restaurant.setRestaurantId(resultSet.getInt("restaurant_id"));
        restaurant.setRestaurantName(resultSet.getString("restaurant_name"));
        restaurant.setCategory(resultSet.getString("category"));
        restaurant.setImageUrl(resultSet.getString("image_url"));
        restaurant.setVisitedCount(resultSet.getInt("visited_count"));
        restaurant.setLastVisitedAt(resultSet.getTimestamp("last_visited_at"));
        restaurant.setNote(resultSet.getString("note"));

        return restaurant;
    }
}
