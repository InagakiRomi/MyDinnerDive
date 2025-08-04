package com.romi.my_dinnerdive.dto;

import jakarta.validation.constraints.NotNull;

/** 用來接收前端傳入的購買的餐點資訊，包含餐點 ID 和數量 */
public class BuyItem {

    /** 餐點 ID */
    @NotNull
    private Integer diseId;

    /** 該餐點的購買數量 */
    @NotNull
    private Integer quantity;

    public Integer getDiseId() {
        return diseId;
    }

    public void setDiseId(Integer diseId) {
        this.diseId = diseId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
