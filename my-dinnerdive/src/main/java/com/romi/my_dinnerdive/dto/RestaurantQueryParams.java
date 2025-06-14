package com.romi.my_dinnerdive.dto;

import com.romi.my_dinnerdive.constant.RestaurantCategory;

public class RestaurantQueryParams {
    
    private RestaurantCategory category;
    private String search;
    private String orderBy;
    private String sort;

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

    public String getOrderBy() {
        return orderBy;
    }

    public void setOrderBy(String orderBy) {
        this.orderBy = orderBy;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }
}
