package com.romi.my_dinnerdive.dao.Impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import com.romi.my_dinnerdive.dao.RestaurantDao;
import com.romi.my_dinnerdive.dto.RestaurantRequest;
import com.romi.my_dinnerdive.model.Restaurant;
import com.romi.my_dinnerdive.rowmapper.RestaurantRowMapper;

/**
 * {@link RestaurantDao} 的實作類別。
 * 
 * 負責操作資料庫中的餐廳資料，包含查詢與新增功能，使用 Spring JDBC 的 {@link NamedParameterJdbcTemplate}
 * 提供命名參數查詢與更新的便利性與安全性。
 */
@Component
public class RestaurantDaoImpl implements RestaurantDao {

    /**
     * 注入 JDBC 模板，用於執行含命名參數的 SQL 查詢與資料更新操作。
     */
    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    /**
     * 根據餐廳 ID 查詢單筆餐廳資料。
     *
     * @param restaurantId 餐廳的唯一識別碼
     * @return 若查詢成功，回傳對應的 {@link Restaurant}；若查無資料則回傳 null。
     */
    @Override
    public Restaurant getRestaurantById(Integer restaurantId) {
        String sql = "SELECT restaurant_id, restaurant_name, category, image_url, visited_count, last_visited_at, note " +
                     "FROM restaurants WHERE restaurant_id = :restaurantId";

        Map<String, Object> map = new HashMap<>();
        map.put("restaurantId", restaurantId);

        List<Restaurant> restaurantList = namedParameterJdbcTemplate.query(sql, map, new RestaurantRowMapper());

        return restaurantList.isEmpty() ? null : restaurantList.get(0);
    }

    /**
     * 建立新的餐廳資料，並回傳由資料庫自動產生的餐廳 ID。
     *
     * @param restaurantRequest 包含新增餐廳所需的各項資訊
     * @return 成功建立後的餐廳主鍵 ID
     */
    @Override
    public Integer createRestaurant(RestaurantRequest restaurantRequest) {
        String sql = "INSERT INTO restaurants (restaurant_name, category, image_url, visited_count, last_visited_at, note) " +
                     "VALUES (:restaurantName, :category, :imageUrl, :visitedCount, :lastVisitedAt, :note)";

        Map<String, Object> map = new HashMap<>();
        map.put("restaurantName", restaurantRequest.getRestaurantName());
        map.put("category", restaurantRequest.getCategory().name());
        map.put("imageUrl", restaurantRequest.getImageUrl());
        map.put("visitedCount", restaurantRequest.getVisitedCount());
        map.put("visitedCount", restaurantRequest.getVisitedCount() != null ? restaurantRequest.getVisitedCount() : 0);
        map.put("note", restaurantRequest.getNote());

        map.put("lastVisitedAt", new Date());

        KeyHolder keyHolder = new GeneratedKeyHolder();

        namedParameterJdbcTemplate.update(sql, new MapSqlParameterSource(map), keyHolder);

        return keyHolder.getKey().intValue();
    }
}