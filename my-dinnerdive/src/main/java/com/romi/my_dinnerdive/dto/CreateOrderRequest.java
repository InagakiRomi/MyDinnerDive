package com.romi.my_dinnerdive.dto;

import java.util.List;

import jakarta.validation.constraints.NotEmpty;

/** 用來接收前端傳入的訂單創建請求，包含購買的餐點列表 */
public class CreateOrderRequest {
    
    /** 購買的餐點列表 */
    @NotEmpty
    private List<BuyItem> buyItemList;

    public List<BuyItem> getBuyItemList() {
        return buyItemList;
    }

    public void setBuyItemList(List<BuyItem> buyItemList) {
        this.buyItemList = buyItemList;
    }
}
