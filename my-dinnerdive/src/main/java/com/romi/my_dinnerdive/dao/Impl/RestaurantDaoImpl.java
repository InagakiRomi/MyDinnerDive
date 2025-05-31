package com.romi.my_dinnerdive.dao.Impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

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

    /**
     * 根據 ID 查詢餐廳資料。
     */
    @Override
    public Restaurant getRestaurantById(Integer restaurantId) {
        String sql = "SELECT restaurant_id, restaurant_name, category, image_url, visited_count, last_eat, last_visited_at, note " +
                     "FROM restaurants WHERE restaurant_id = :restaurantId";

        Map<String, Object> map = new HashMap<>();
        map.put("restaurantId", restaurantId);

        List<Restaurant> restaurantList = namedParameterJdbcTemplate.query(sql, map, new RestaurantRowMapper());

        if(!restaurantList.isEmpty()){
            return restaurantList.get(0);
        } else{
            return null;
        }
    }

    /**
     * 新增餐廳資料，並回傳自動產生的主鍵 ID。
     */
    @Override
    public Integer createRestaurant(RestaurantRequest restaurantRequest) {
        String sql = "INSERT INTO restaurants (restaurant_name, category, image_url, last_eat, last_visited_at, note) " +
                     "VALUES (:restaurantName, :category, :imageUrl, :lastEat, :lastVisitedAt, :note)";

        Map<String, Object> map = new HashMap<>();
        map.put("restaurantName", restaurantRequest.getRestaurantName());
        map.put("category", restaurantRequest.getCategory().name());
        map.put("imageUrl", restaurantRequest.getImageUrl());
        map.put("lastEat", restaurantRequest.getLastEat());
        map.put("lastVisitedAt", new Date());
        map.put("note", restaurantRequest.getNote());

        KeyHolder keyHolder = new GeneratedKeyHolder();
        namedParameterJdbcTemplate.update(sql, new MapSqlParameterSource(map), keyHolder);

        // 檢查 keyHolder 是否為 null 或沒有 key，如果是則拋出 NullPointerException
        Number key = Objects.requireNonNull(keyHolder.getKey(), "找不到 keyHolder 的主鍵，請檢查 SQL 或資料庫設定。");
        return key.intValue();
    }

    /**
     * 取得所有餐廳資料ID。
     */
    @Override
    public List<Integer> getAllRestaurantIds() {
        String idSql = "SELECT restaurant_id FROM restaurants";
        return  namedParameterJdbcTemplate.query(idSql, (rs, rowNum) -> rs.getInt("restaurant_id"));
    }
}