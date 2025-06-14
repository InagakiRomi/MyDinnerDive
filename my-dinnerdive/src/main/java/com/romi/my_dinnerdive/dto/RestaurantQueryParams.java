package com.romi.my_dinnerdive.dto;

import com.romi.my_dinnerdive.constant.RestaurantCategory;

public class RestaurantQueryParams {
    
    private RestaurantCategory category;
    private String search;

    public RestaurantCategory getCategory() {
        return category;
    }

    public void setCategory(RestaurantCategory category) {
        this.category = category;
    }

    public String getSearch() {
        return search;
    }

    public void setSearch(String search) {
        this.search = search;
    }
}
