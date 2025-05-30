package com.romi.my_dinnerdive.dao.Impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Random;

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
     * 用於儲存上次抽到的餐廳 ID，這樣可以避免連續抽到同一個餐廳。
     */
    private ArrayList<Integer> lastId = new ArrayList<>();

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
     * 隨機選取一筆餐廳資料回傳。
     */
    @Override
    public Restaurant findRandomRestaurant() {
        String idSql = "SELECT restaurant_id FROM restaurants";
        List<Integer> idList = namedParameterJdbcTemplate.query(idSql, (rs, rowNum) -> rs.getInt("restaurant_id"));

        // 如果已經抽完所有餐廳，則清空 lastId 並重新開始抽
        if(lastId.size() == idList.size()){
            lastId.clear();
            System.out.println("所有餐廳已被抽完，重新開始抽籤。");
        }

        // 如果有抽過，則過濾掉上次抽到的 ID
        if(!lastId.isEmpty()){
            idList.removeAll(lastId);
            System.out.println("目前有 "+idList.size()+" 個ID尚未開始抽");
        }

        // 抽出餐廳 ID
        Random random = new Random();
        int randomId =random.nextInt(idList.size());
        randomId = idList.get(randomId);

        // 儲存本次抽到的餐廳ID
        lastId.add(randomId);

        // 未抽過的餐廳數量
        int lastFood = idList.size()-1;

        // 顯示資訊
        System.out.println("本次抽到的食物ID為： "+randomId);
        System.out.println("目前還有 "+ lastFood + " 個餐廳可以抽");
        System.out.println("目前被抽過的餐廳ID有： "+ lastId);
        return getRestaurantById(randomId); 
    }
}