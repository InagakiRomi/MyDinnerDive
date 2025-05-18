package com.romi.my_dinnerdive.model;

import java.util.Date;

import com.romi.my_dinnerdive.constant.RestaurantCategory;

public class Restaurant {
    private Integer restaurantId;
    private String restaurantName;
    private RestaurantCategory category;
    private String imageUrl;
    private Integer visitedCount;
    private Date lastVisitedAt;
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
