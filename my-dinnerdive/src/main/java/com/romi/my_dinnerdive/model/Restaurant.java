package com.romi.my_dinnerdive.model;

import java.util.Date;

import com.romi.my_dinnerdive.constant.RestaurantCategory;

/**
 * 資料模型：Restaurant
 *
 * 對應資料庫中的 {@code restaurants} 資料表，用於封裝單筆餐廳的所有欄位資料。
 * 主要作為資料存取層（DAO）與業務邏輯層（Service）之間的資料傳輸物件（POJO）。
 */
public class Restaurant {

    /**
     * 餐廳的唯一識別碼（Primary Key）
     */
    private Integer restaurantId;

    /**
     * 餐廳名稱
     */
    private String restaurantName;

    /**
     * 餐廳類別（枚舉型別）
     */
    private RestaurantCategory category;

    /**
     * 餐廳圖片的網址
     */
    private String imageUrl;

    /**
     * 選擇次數統計
     */
    private Integer visitedCount;

    /**
     * 最後一次選擇該食物的時間
     */
    private Date lastEat;

    /**
     * 最後一次更新資料的時間
     */
    private Date lastVisitedAt;

    /**
     * 餐廳備註
     */
    private String note;

    public Integer getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(Integer restaurantId) {
        this.restaurantId = restaurantId;
    }

    public String getRestaurantName() {
        return restaurantName;
    }

    public void setRestaurantName(String restaurantName) {
        this.restaurantName = restaurantName;
    }

    public RestaurantCategory getCategory() {
        return category;
    }

    public void setCategory(RestaurantCategory category) {
        this.category = category;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Integer getVisitedCount() {
        return visitedCount;
    }

    public void setVisitedCount(Integer visitedCount) {
        this.visitedCount = visitedCount;
    }

    public Date getLastEat() {
        return lastEat;
    }

    public void setLastEat(Date lastEat) {
        this.lastEat = lastEat;
    }

    public Date getLastVisitedAt() {
        return lastVisitedAt;
    }

    public void setLastVisitedAt(Date lastVisitedAt) {
        this.lastVisitedAt = lastVisitedAt;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}