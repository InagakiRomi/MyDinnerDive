package com.romi.my_dinnerdive.model;

/** 餐點資料模型（對應資料表 dishes），主要用於 DAO ↔ Service 間的資料傳遞，封裝一筆餐點的所有資料 */
public class Dish {

    /** 餐點 ID */
    private Integer dishId;

    /** 餐點對應餐廳編號 */
    private Integer restaurantId;

    /** 餐點對應餐點價格 */
    private Integer price;
    
    /** 餐點名稱 */
    private String dishName;

    public Integer getDishId() {
        return dishId;
    }

    public void setDishId(Integer dishId) {
        this.dishId = dishId;
    }

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
