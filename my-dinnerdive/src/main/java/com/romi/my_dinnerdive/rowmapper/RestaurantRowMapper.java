package com.romi.my_dinnerdive.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.lang.NonNull;

import com.romi.my_dinnerdive.constant.RestaurantCategory;
import com.romi.my_dinnerdive.model.Restaurant;

/**
 * 資料列對應器：RestaurantRowMapper
 *
 * 將查詢結果 {@link ResultSet} 中的資料列映射為 {@link Restaurant} 物件。
 * 此類別主要配合 {@link org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate}
 * 使用於查詢餐廳資料的轉換過程。
 */
public class RestaurantRowMapper implements RowMapper<Restaurant> {

    /**
     * 將單筆資料列轉換為 {@link Restaurant} 實體。
     *
     * @param resultSet 查詢結果的 ResultSet
     * @param i 資料列索引（由 Spring 傳入）
     * @return 映射後的 Restaurant 實例
     * @throws SQLException 若資料存取有誤則拋出例外
     */
    @Override
    public Restaurant mapRow(@NonNull ResultSet resultSet, int i) throws SQLException {
        Restaurant restaurant = new Restaurant();

        restaurant.setRestaurantId(resultSet.getInt("restaurant_id"));
        restaurant.setRestaurantName(resultSet.getString("restaurant_name"));

        String categoryStr = resultSet.getString("category");
        RestaurantCategory category = RestaurantCategory.valueOf(categoryStr);
        restaurant.setCategory(category);

        restaurant.setImageUrl(resultSet.getString("image_url"));
        restaurant.setVisitedCount(resultSet.getInt("visited_count"));
        restaurant.setLastEat(resultSet.getTimestamp("last_eat"));
        restaurant.setLastVisitedAt(resultSet.getTimestamp("last_visited_at"));
        restaurant.setNote(resultSet.getString("note"));

        return restaurant;
    }
}