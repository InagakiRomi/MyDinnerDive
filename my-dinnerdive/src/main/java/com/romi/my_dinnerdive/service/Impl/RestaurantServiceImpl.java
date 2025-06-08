package com.romi.my_dinnerdive.service.Impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.romi.my_dinnerdive.dao.RestaurantDao;
import com.romi.my_dinnerdive.dto.RestaurantRequest;
import com.romi.my_dinnerdive.logging.LoggingDemo;
import com.romi.my_dinnerdive.model.Restaurant;
import com.romi.my_dinnerdive.service.RestaurantService;

/**
 * 餐廳服務的實作類別，實作商業邏輯與資料處理流程。
 */
@Service
public class RestaurantServiceImpl implements RestaurantService {

    @Autowired
    private RestaurantDao restaurantDao;

    @Autowired
    private LoggingDemo loggingDemo;

    /**
     * 用於儲存上次抽到的餐廳 ID，這樣可以避免連續抽到同一個餐廳。
     */
    private ArrayList<Integer> lastId = new ArrayList<>();

    /**
     * 取得所有餐廳資料。
     */
    @Override
    public List<Restaurant> getRestaurants(){
        return restaurantDao.getRestaurants();
    }

    /**
     * 根據餐廳 ID 查詢資料。
     */
    @Override
    public Restaurant getRestaurantById(Integer restaurantId) {
        return restaurantDao.getRestaurantById(restaurantId);
    }

    /**
     * 建立新的餐廳資料。
     */
    @Override
    public Integer createRestaurant(RestaurantRequest restaurantRequest) {
        return restaurantDao.createRestaurant(restaurantRequest);
    }

    /**
     * 修改指定 ID 的餐廳資料。
     */
    @Override
    public void updateRestaurant(Integer restaurantId, RestaurantRequest restaurantRequest){
        restaurantDao.updateRestaurant(restaurantId, restaurantRequest);
    }

    /**
     * 刪除指定 ID 的餐廳資料。
     */
    @Override
    public void deleteRestaurantById(Integer restaurantId){
        restaurantDao.deleteRestaurantById(restaurantId);
    }

    /**
     * 隨機取得一筆餐廳資料。
     */
    @Override
    public List<Integer> getAllRestaurantIds(){
        return restaurantDao.getAllRestaurantIds();
    }

    /**
     * 隨機取得一筆餐廳資料。
     */
    @Override
    public Restaurant getRandomRestaurant() {
        List<Integer> idList = restaurantDao.getAllRestaurantIds();
        Logger logger = loggingDemo.printRandomRestaurantLog();
        
        // 如果已經抽完所有餐廳，則清空 lastId 並重新開始抽
        if(lastId.size() == idList.size()){
            clearRandomRestaurant();
            logger.log(Level.INFO, "已抽完所有餐廳重新開始抽");
        }

        // 如果有抽過，則過濾掉上次抽到的 ID
        if(!lastId.isEmpty()){
            idList.removeAll(lastId);
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
        logger.log(Level.FINE,"本次抽到的食物ID為： " + randomId);
        logger.log(Level.FINE,"目前還有 " + lastFood + " 個餐廳可以抽");
        logger.log(Level.FINE,"目前被抽過的餐廳ID有： " + lastId);
        return getRestaurantById(randomId);
    }

    /**
     * 清空抽籤資料。
     */
    @Override
    public void clearRandomRestaurant() {
        Logger logger = loggingDemo.printRandomRestaurantLog();
        
        if(!lastId.isEmpty()) {
            lastId.clear();
            logger.log(Level.INFO, "清空抽籤資料");
        }
    }
}