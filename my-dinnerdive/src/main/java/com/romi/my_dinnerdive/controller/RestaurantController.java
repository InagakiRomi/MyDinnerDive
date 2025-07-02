package com.romi.my_dinnerdive.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.romi.my_dinnerdive.constant.RestaurantCategory;
import com.romi.my_dinnerdive.dto.RestaurantRequest;
import com.romi.my_dinnerdive.dto.RestaurantQueryParams;
import com.romi.my_dinnerdive.model.Restaurant;
import com.romi.my_dinnerdive.service.RestaurantService;
import com.romi.my_dinnerdive.util.Page;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;

import org.springframework.web.bind.annotation.PutMapping;


/**
 * 控制器類別：RestaurantController
 * 
 * 提供與餐廳相關的 RESTful API，包含查詢特定餐廳資料及新增餐廳紀錄的功能。
 */

@Validated
@RestController
public class RestaurantController {

    @Autowired
    private RestaurantService restaurantService;


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
    @GetMapping("/restaurants")
    public ResponseEntity<Page<Restaurant>> getRestaurants(
            // 查詢條件        
            @RequestParam(required = false) RestaurantCategory category,
            @RequestParam(required = false) String search,

            // 排序
            @RequestParam(defaultValue = "restaurant_id") String orderBy,
            @RequestParam(defaultValue = "ASC") String sort,

            //分頁
            @RequestParam(defaultValue = "10") @Max(1000) @Min(0) Integer limit,
            @RequestParam(defaultValue = "0")  @Min(0) Integer offset
    ) {
        RestaurantQueryParams restaurantQueryParams = new RestaurantQueryParams();
        restaurantQueryParams.setCategory(category);
        restaurantQueryParams.setSearch(search);
        restaurantQueryParams.setOrderBy(orderBy);
        restaurantQueryParams.setSort(sort);
        restaurantQueryParams.setLimit(limit);
        restaurantQueryParams.setOffset(offset);
        
        // 取得 restaurant list
        List<Restaurant> restaurantList = restaurantService.getRestaurants(restaurantQueryParams);

        // 取得 restaurant 總數
        Integer total = restaurantService.countRestaurant(restaurantQueryParams);

        // 分頁
        Page<Restaurant> page = new Page<>();
        page.setLimit(limit);
        page.setOffset(offset);
        page.setTotal(total);
        page.setResults(restaurantList);

        return ResponseEntity.status(HttpStatus.OK).body(page);
    }

    /**
     * 根據餐廳 ID 查詢資料。
     *
     * @param restaurantId 餐廳的唯一識別碼
     * @return 若查詢成功，回傳對應餐廳資料與 HTTP 200；否則回傳 HTTP 404。
     */
    @GetMapping("/restaurants/{restaurantId}")
    public ResponseEntity<Restaurant> getRestaurant(@PathVariable Integer restaurantId){
        Restaurant restaurant = restaurantService.getRestaurantById(restaurantId);

        if(restaurant != null){
            return ResponseEntity.status(HttpStatus.OK).body(restaurant);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    /**
     * 建立新的餐廳資料。
     *
     * @param restaurantRequest 新增餐廳所需的請求資料，需通過驗證
     * @return 回傳建立後的餐廳資料與 HTTP 201 狀態碼
     */
    @PostMapping(value = "/restaurants")
    public ResponseEntity<Restaurant> createRestaurant(@RequestBody @Valid RestaurantRequest restaurantRequest,
                                                       @RequestParam(defaultValue = "0") Integer visitedCount) {
        Integer restaurantId = restaurantService.createRestaurant(restaurantRequest);
        Restaurant restaurant = restaurantService.getRestaurantById(restaurantId);
        return ResponseEntity.status(HttpStatus.CREATED).body(restaurant);
    }

    /**
     * 修改指定 ID 的餐廳資料。
     *
     * @param restaurantId 餐廳的唯一識別碼
     * @param restaurantRequest 修改餐廳所需的請求資料，需通過驗證
     * @return 回傳修改後的餐廳資料與 HTTP 200 狀態碼
     */
    @PutMapping("/restaurants/{restaurantId}")
    public ResponseEntity<Restaurant> updateRestaurant(@PathVariable Integer restaurantId,
                                                       @RequestBody @Valid RestaurantRequest restaurantRequest) {
        Restaurant restaurant = restaurantService.getRestaurantById(restaurantId);

        // 檢查 restaurant 是否存在
        if (restaurant == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        // 修改商品的數據
        restaurantService.updateRestaurant(restaurantId, restaurantRequest);
        Restaurant updatedRestaurant = restaurantService.getRestaurantById(restaurantId);
        return ResponseEntity.status(HttpStatus.OK).body(updatedRestaurant);
    }

    /**
     * 刪除指定 ID 的餐廳資料。
     *
     * @param restaurantId 餐廳的唯一識別碼
     * @return 若刪除成功，回傳 HTTP 204；若餐廳不存在，則回傳 HTTP 404。
     */
    @DeleteMapping("/restaurants/{restaurantId}")
    public ResponseEntity<?> deleteRestaurant(@PathVariable Integer restaurantId) {
        restaurantService.deleteRestaurantById(restaurantId);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    /**
     * 抽選一筆隨機餐廳
     */
    @GetMapping("/random")
    public Restaurant getRandomRestaurant(@RequestParam(required = false) RestaurantCategory category) {
        RestaurantQueryParams params = new RestaurantQueryParams();
        params.setCategory(category);
        return restaurantService.getRandomRestaurant(params);
    }

    /**
     * 清空抽籤資料。
     *
     * @return 清除後的餐廳資料列表，若無資料則回傳 null
     */
    @GetMapping("/clearRandom")
    public void clearRandomRestaurant() {
        restaurantService.clearRandomRestaurant();
    }

    /**
     * 選擇一間餐廳並更新其選擇紀錄。
     * <p>
     * 此方法會選出一間餐廳作為本次選擇結果，並將該餐廳的選擇次數加一，最後選擇時間更新為目前時間。
     */
    @PatchMapping("/choose/{restaurantId}")
    public ResponseEntity<Restaurant> chooseRestaurant(@PathVariable Integer restaurantId) {
        Restaurant restaurant = restaurantService.getRestaurantById(restaurantId);

        // 檢查 restaurant 是否存在
        if (restaurant == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        // 修改商品的數據
        restaurantService.chooseRestaurant(restaurantId);
        Restaurant chooseRestaurant = restaurantService.getRestaurantById(restaurantId);
        return ResponseEntity.status(HttpStatus.OK).body(chooseRestaurant);
    }
}