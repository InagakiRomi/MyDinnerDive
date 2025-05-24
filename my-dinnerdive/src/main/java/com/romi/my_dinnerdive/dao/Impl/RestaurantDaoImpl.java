package com.romi.my_dinnerdive.dao.Impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.romi.my_dinnerdive.dao.RestaurantDao;
import com.romi.my_dinnerdive.dto.RestaurantRequest;
import com.romi.my_dinnerdive.model.Restaurant;
import com.romi.my_dinnerdive.rowmapper.RestaurantRowMapper;

@Repository
public class RestaurantDaoImpl implements RestaurantDao {

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    // 共用的欄位 SQL
    private static final String BASE_SELECT_SQL = 
        "SELECT restaurant_id, restaurant_name, category, image_url, visited_count, last_eat, last_visited_at, note ";

    /**
     * 根據 ID 查詢餐廳資料。
     */
    @Override
    public Restaurant getRestaurantById(Integer restaurantId) {
        return querySingleRestaurant(restaurantId);
    }

    /**
     * 新增餐廳資料，並回傳自動產生的主鍵 ID。
     */
    @Override
    public Integer createRestaurant(RestaurantRequest restaurantRequest) {
        String sql = "INSERT INTO restaurants (restaurant_name, category, image_url, visited_count, last_eat, last_visited_at, note) " +
                     "VALUES (:restaurantName, :category, :imageUrl, :visitedCount, :lastEat, :lastVisitedAt, :note)";

        Map<String, Object> map = new HashMap<>();
        map.put("restaurantName", restaurantRequest.getRestaurantName());
        map.put("category", restaurantRequest.getCategory().name());
        map.put("imageUrl", restaurantRequest.getImageUrl());
        map.put("visitedCount", restaurantRequest.getVisitedCount() != null ? restaurantRequest.getVisitedCount() : 0);
        map.put("lastEat", restaurantRequest.getLastEat());
        map.put("lastVisitedAt", new Date());
        map.put("note", restaurantRequest.getNote());

        KeyHolder keyHolder = new GeneratedKeyHolder();
        namedParameterJdbcTemplate.update(sql, new MapSqlParameterSource(map), keyHolder);

        return keyHolder.getKey().intValue();
    }

    /**
     * 隨機選取一筆餐廳資料回傳。
     */
    @Override
    public Restaurant findRandomRestaurant() {
        String idSql = "SELECT restaurant_id FROM restaurants";
        List<Integer> idList = namedParameterJdbcTemplate.query(idSql, (rs, rowNum) -> rs.getInt("restaurant_id"));

        if (idList.isEmpty()) {
            return null;
        }

        int randomId = idList.get(ThreadLocalRandom.current().nextInt(idList.size()));
        return querySingleRestaurant(randomId);
    }

    /**
     * 抽出共用查詢邏輯：根據餐廳 ID 查詢一筆資料
     */
    private Restaurant querySingleRestaurant(Integer restaurantId) {
        String sql = BASE_SELECT_SQL + "FROM restaurants WHERE restaurant_id = :restaurantId";
        Map<String, Object> param = Map.of("restaurantId", restaurantId);

        List<Restaurant> result = namedParameterJdbcTemplate.query(sql, param, new RestaurantRowMapper());
        return result.isEmpty() ? null : result.get(0);
    }
}