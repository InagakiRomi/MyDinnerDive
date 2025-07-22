package com.romi.my_dinnerdive.dto;

import java.util.Date;

import com.romi.my_dinnerdive.constant.RestaurantCategory;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

/** 用於接收前端傳送過來的 JSON 資料，新增或修改餐廳時使用 */
public class RestaurantRequest {

    /** 餐廳名稱，不可為空 */
    @NotBlank
    private String restaurantName;

    /** 餐廳分類 */
    @NotNull
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