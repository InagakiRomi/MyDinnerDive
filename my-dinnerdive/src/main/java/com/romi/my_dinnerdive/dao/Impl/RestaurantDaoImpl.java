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
import com.romi.my_dinnerdive.dto.RestaurantQueryParams;
import com.romi.my_dinnerdive.dto.RestaurantRequest;
import com.romi.my_dinnerdive.model.Restaurant;
import com.romi.my_dinnerdive.rowmapper.RestaurantRowMapper;

@Repository
public class RestaurantDaoImpl implements RestaurantDao {

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Override
    public Integer countRestaurant(RestaurantQueryParams restaurantQueryParams){
        String sql = "SELECT count(*) FROM restaurants WHERE 1=1";
        Map<String, Object> map = new HashMap<>();

        // 查詢條件
        sql = addFilteringSql(sql, map, restaurantQueryParams);

        Integer total = namedParameterJdbcTemplate.queryForObject(sql, map, Integer.class);

        return total;
    }

    @Override
    public List<Restaurant> getRestaurants(RestaurantQueryParams restaurantQueryParams){
        String sql = "SELECT restaurant_id, restaurant_name, category, image_url, visited_count, last_eat, last_visited_at, note " +
                     "FROM restaurants WHERE 1=1";

        Map<String, Object> map = new HashMap<>();

        // 查詢條件
        sql = addFilteringSql(sql, map, restaurantQueryParams);

        // 排序
        sql = sql + " ORDER BY " + restaurantQueryParams.getOrderBy() + " " + restaurantQueryParams.getSort();

        // 分頁
        sql = sql + " LIMIT :limit OFFSET :offset";
        map.put("limit", restaurantQueryParams.getLimit());
        map.put("offset", restaurantQueryParams.getOffset());

        List<Restaurant> restaurantList = namedParameterJdbcTemplate.query(sql, map, new RestaurantRowMapper());
    
        return restaurantList;
    }

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

    public void updateRestaurant(Integer restaurantId, RestaurantRequest restaurantRequest){
        String sql = "UPDATE restaurants SET restaurant_name = :restaurantName, category = :category, " +
                     "visited_count = :visitedCount, last_eat = :lastEat, note = :note, last_visited_at = :lastVisitedAt, image_url = :imageUrl " +
                     "WHERE restaurant_id = :restaurantId";

        Map<String, Object> map = new HashMap<>();
        map.put("restaurantId", restaurantId);

        map.put("restaurantName", restaurantRequest.getRestaurantName());
        map.put("category", restaurantRequest.getCategory().toString());
        map.put("visitedCount", restaurantRequest.getVisitedCount());
        map.put("lastEat", restaurantRequest.getLastEat());
        map.put("note", restaurantRequest.getNote());
        map.put("imageUrl", restaurantRequest.getImageUrl());

        map.put("lastVisitedAt", new Date());

        namedParameterJdbcTemplate.update(sql, map);
    }

    @Override
    public void deleteRestaurantById(Integer restaurantId){
        String sql = "DELETE FROM restaurants WHERE restaurant_id = :restaurantId";

        Map<String, Object> map = new HashMap<>();
        map.put("restaurantId", restaurantId);

        namedParameterJdbcTemplate.update(sql, map);
    }

    @Override
    public List<Integer> getAllRestaurantIds() {
        String idSql = "SELECT restaurant_id FROM restaurants";
        return  namedParameterJdbcTemplate.query(idSql, (rs, rowNum) -> rs.getInt("restaurant_id"));
    }

    @Override
    public void chooseRestaurant(Integer restaurantId, RestaurantRequest restaurantRequest){
        String sql = "UPDATE restaurants SET restaurant_name = :restaurantName, category = :category, " +
                     "visited_count = :visitedCount, last_eat = :lastEat, last_visited_at = :lastVisitedAt " +
                     "WHERE restaurant_id = :restaurantId";
        
        Map<String, Object> map = new HashMap<>();
        map.put("restaurantId", restaurantId);

        map.put("restaurantName", restaurantRequest.getRestaurantName());
        map.put("category", restaurantRequest.getCategory().toString());

        Integer visitedCount;
        if (restaurantRequest.getVisitedCount() != null) {
            visitedCount = restaurantRequest.getVisitedCount();
        }
        else{
            visitedCount = 0;
        }
        map.put("visitedCount", visitedCount +1);  

        map.put("lastEat", new Date());
        map.put("lastVisitedAt", new Date());

        namedParameterJdbcTemplate.update(sql, map);
    }

    private String addFilteringSql(String sql, Map<String, Object> map, RestaurantQueryParams restaurantQueryParams){
        // 查詢條件
        if (restaurantQueryParams.getCategory() != null) {
            sql += " AND category = :category";
            map.put("category", restaurantQueryParams.getCategory().name());
        }

        if (restaurantQueryParams.getSearch() != null) {
            sql += " AND (restaurant_name LIKE :search OR note LIKE :search)";
            map.put("search", "%" + restaurantQueryParams.getSearch() + "%");
        }

        return sql;
    }
}