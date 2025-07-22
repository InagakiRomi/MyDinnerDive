package com.romi.my_dinnerdive.model;

import java.util.Date;

import com.romi.my_dinnerdive.constant.RestaurantCategory;

/** 餐廳資料模型（對應資料表 restaurants），主要用於 DAO ↔ Service 間的資料傳遞，封裝一筆餐廳的所有欄位 */
public class Restaurant {

    /** 餐廳 ID */
    private Integer restaurantId;

    /** 餐廳名稱 */
    private String restaurantName;

    /** 餐廳分類 */
    private RestaurantCategory category;

    /** 餐廳圖片URL */
    private String imageUrl;

    /** 選擇次數 */
    private Integer visitedCount;

    /** 最後一次選擇時間 */
    private Date lastSelectedAt;

    /** 最後一次更新時間 */
    private Date updatedAt;

    /** 備註欄 */
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

    public Date getLastSelectedAt() {
        return lastSelectedAt;
    }

    public void setLastSelectedAt(Date lastSelectedAt) {
        this.lastSelectedAt = lastSelectedAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}