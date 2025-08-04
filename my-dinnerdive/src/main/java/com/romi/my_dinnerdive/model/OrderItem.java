package com.romi.my_dinnerdive.model;

/** 購物車明細表資料模型（對應資料表 order_item），主要用於 DAO ↔ Service 間的資料傳遞，封裝一筆餐點的所有資料 */
public class OrderItem {

    /** 訂單項目編號 */
    private Integer orderItemId;

    /** 對應的訂單編號 */
    private Integer orderId;

    /** 餐點 ID */
    private Integer dishId;

    /** 該餐點的購買數量 */
    private Integer quantity;

    /** 該餐點的總金額 */
    private Integer amount;

    public Integer getOrderItemId() {
        return orderItemId;
    }
    public void setOrderItemId(Integer orderItemId) {
        this.orderItemId = orderItemId;
    }
    public Integer getOrderId() {
        return orderId;
    }
    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }
    public Integer getDishId() {
        return dishId;
    }
    public void setDishId(Integer dishId) {
        this.dishId = dishId;
    }
    public Integer getQuantity() {
        return quantity;
    }
    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
    public Integer getAmount() {
        return amount;
    }
    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    
}
