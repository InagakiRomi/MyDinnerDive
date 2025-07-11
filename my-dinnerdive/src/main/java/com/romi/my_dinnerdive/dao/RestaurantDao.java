package com.romi.my_dinnerdive.dao;

import java.util.List;

import com.romi.my_dinnerdive.dto.RestaurantQueryParams;
import com.romi.my_dinnerdive.dto.RestaurantRequest;
import com.romi.my_dinnerdive.model.Restaurant;

/**
 * 餐廳 DAO 介面，定義與資料庫互動的方法。
 */
public interface RestaurantDao {
    
    /**
     * 根據查詢條件取得符合條件的餐廳總數。
     *
     * @param restaurantQueryParams 查詢餐廳所使用的參數（如名稱、類型等）
     * @return 符合查詢條件的餐廳總數
     */
    Integer countRestaurant(RestaurantQueryParams restaurantQueryParams);

    /**
     * 取得餐廳列表的 API。
     * 支援條件查詢、排序與分頁功能。
     *
     * @param category 餐廳分類，可選參數
     * @param search 搜尋關鍵字，用於餐廳名稱或其他欄位的模糊查詢，可選參數
     * @param orderBy 排序欄位，預設為 restaurant_id
     * @param sort 排序方式，"ASC" 為升冪，"DESC" 為降冪，預設為 "ASC"
     * @param limit 每頁筆數，預設為 10，最大值為 1000，最小值為 0
     * @param offset 起始頁面偏移值，預設為 0，最小值為 0
     * @return 回傳包含分頁資訊與餐廳資料的 Page 物件
     */
    List<Restaurant> getRestaurants(RestaurantQueryParams restaurantQueryParams);

    /**
     * 根據 ID 查詢餐廳。
     *
     * @param restaurantId 餐廳 ID
     * @return 對應的餐廳資料，若無資料則為 null
     */
    Restaurant getRestaurantById(Integer restaurantId);

    /**
     * 建立一筆餐廳資料。
     *
     * @param restaurantRequest 新增餐廳請求物件
     * @return 資料庫產生的餐廳 ID
     */
    Integer createRestaurant(RestaurantRequest restaurantRequest);

    /**
     * 修改指定 ID 的餐廳資料。
     *
     * @param restaurantId 餐廳的唯一識別碼
     * @param restaurantRequest 更新的餐廳請求物件
     */
    void updateRestaurant(Integer restaurantId, RestaurantRequest restaurantRequest);

    /**
     * 刪除指定 ID 的餐廳資料。
     * 
     * @return 無回傳值，若無資料則不做任何操作
     */
    void deleteRestaurantById(Integer restaurantId);

    /**
     * 取得所有餐廳資料ID。
     *
     * @return 所有餐廳資料ID，若無資料則回傳 null
     */
    List<Integer> getAllRestaurantIds(RestaurantQueryParams restaurantQueryParams);

    /**
     * 選擇一間餐廳並更新其選擇紀錄。
     * <p>
     * 此方法會選出一間餐廳作為本次選擇結果，並將該餐廳的選擇次數加一，最後選擇時間更新為目前時間。
     *
     * @return 被選中的餐廳物件（Restaurant）
     */
    void chooseRestaurant(Integer restaurantId);
}