package com.romi.my_dinnerdive.dto;

import java.util.Date;

import com.romi.my_dinnerdive.model.Restaurant;

public class RestaurantResponse {

    private Integer restaurantId;
    private String restaurantName;
    private String  category;
    private String imageUrl;
    private Integer visitedCount;
    private Date lastSelectedAt;
    private Date updatedAt;
    private String note;

    public RestaurantResponse(Restaurant restaurant) {
        this.restaurantId = restaurant.getRestaurantId();
        this.restaurantName = restaurant.getRestaurantName();
        this.category = restaurant.getCategory().getDisplayName();
        this.imageUrl = restaurant.getImageUrl();
        this.visitedCount = restaurant.getVisitedCount();
        this.lastSelectedAt = restaurant.getLastSelectedAt();
        this.updatedAt = restaurant.getUpdatedAt();
        this.note = restaurant.getNote();
    }

    public Integer getRestaurantId() {
        return restaurantId;
    }

    public String getRestaurantName() {
        return restaurantName;
    }

    public String getCategory() {
        return category;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public Integer getVisitedCount() {
        return visitedCount;
    }

    public Date getLastSelectedAt() {
        return lastSelectedAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public String getNote() {
        return note;
    }
}
