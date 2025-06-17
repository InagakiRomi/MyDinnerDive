package com.romi.my_dinnerdive.dto;

import com.romi.my_dinnerdive.constant.RestaurantCategory;

/**
 * 餐廳查詢參數封裝類。
 *
 * 專門用於接收前端查詢餐廳清單時的各種條件，例如類別篩選、關鍵字搜尋、
 * 排序依據與順序，以及分頁設定（限制筆數與起始位置）。
 */
public class RestaurantQueryParams {

    /**
     * 餐廳類別（如主食、飲料等），可用來篩選特定類別的餐廳。
     */
    private RestaurantCategory category;

    /**
     * 關鍵字搜尋，依據餐廳名稱或其他欄位進行模糊查詢。
     */
    private String search;

    /**
     * 排序欄位，例如根據「訪問次數」、「最後用餐時間」等欄位排序。
     */
    private String orderBy;

    /**
     * 排序方向，通常為 ASC（升冪）或 DESC（降冪）。
     */
    private String sort;

    /**
     * 限制查詢結果的筆數（用於分頁）。
     */
    private Integer limit;

    /**
     * 查詢起始位置的偏移值（用於分頁）。
     */
    private Integer offset;

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

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public Integer getOffset() {
        return offset;
    }

    public void setOffset(Integer offset) {
        this.offset = offset;
    }
}
