package com.romi.my_dinnerdive.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class DishRequest {

    /** 餐點對應餐廳編號 */
    @NotNull
    private Integer restaurantId;

    /** 餐點對應餐點價格 */
    @NotNull
    private Integer price;
    
    /** 餐點名稱 */
    @NotBlank
    private String dishName;

    public Integer getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(Integer restaurantId) {
        this.restaurantId = restaurantId;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getDishName() {
        return dishName;
    }

    public void setDishName(String dishName) {
        this.dishName = dishName;
    }
}
