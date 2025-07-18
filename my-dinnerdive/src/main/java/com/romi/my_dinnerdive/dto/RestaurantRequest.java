package com.romi.my_dinnerdive.dto;

import java.util.Date;

import com.romi.my_dinnerdive.constant.RestaurantCategory;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

/**
 * 請求資料模型：RestaurantRequest
 * 
 * 用於接收前端送出的 JSON 請求資料，作為建立餐廳資訊的輸入物件。
 */
public class RestaurantRequest {

    /**
     * 餐廳名稱，不可為空。
     */
    @NotBlank
    private String restaurantName;

    /**
     * 餐廳類別（列舉型態），不可為空。
     */
    @NotNull
    private RestaurantCategory category;

    /**
     * 餐廳圖片 URL，可為空。
     */
    private String imageUrl;

    /**
     * 選擇次數，可為空。
     */
    private Integer visitedCount;

    /**
     * 最後一次選擇時間，可為空。
     */
    private Date lastSelectedAt;

    /**
     * 最後一次更新時間，可為空。
     */
    private Date updatedAt;

    /**
     * 備註欄，可為空。
     */
    private String note;

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