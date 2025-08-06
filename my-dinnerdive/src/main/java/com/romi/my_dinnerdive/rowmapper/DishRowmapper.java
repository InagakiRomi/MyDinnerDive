package com.romi.my_dinnerdive.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.lang.NonNull;

import com.romi.my_dinnerdive.model.Dish;

/**
 * RowMapper 實作：將 ResultSet 中的資料轉成 Dish 物件
 * <p>
 * 用於 NamedParameterJdbcTemplate 查詢餐點時的資料轉換邏輯
 */
public class DishRowmapper implements RowMapper<Dish>{
    @Override
    public Dish mapRow(@NonNull ResultSet resultSet, int i) throws SQLException {
        Dish dish = new Dish();
        dish.setDishId(resultSet.getInt("dish_id"));
        dish.setRestaurantId(resultSet.getInt("restaurant_id"));
        dish.setPrice(resultSet.getInt("price"));  
        dish.setDishName(resultSet.getString("dish_name"));

        return dish;
    }
}
