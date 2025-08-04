package com.romi.my_dinnerdive.model;

import java.util.Date;

/** 購物車資料模型（對應資料表 orders），主要用於 DAO ↔ Service 間的資料傳遞，封裝一筆餐點的所有資料 */
public class Orders {

    /** 訂單編號 */
    private Integer orderId;

    /** 下訂單的用戶 ID */
    private Integer userId;

    /** 訂單總金額 */
    private Integer totalAmount;

    /** 訂單建立時間 */
    private Date createdDate;
    
    /** 訂單最後修改時間 */
    private Date lastModifiedDate;
    
    public Integer getOrderId() {
        return orderId;
    }
    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }
    public Integer getUserId() {
        return userId;
    }
    public void setUserId(Integer userId) {
        this.userId = userId;
    }
    public Integer getTotalAmount() {
        return totalAmount;
    }
    public void setTotalAmount(Integer totalAmount) {
        this.totalAmount = totalAmount;
    }
    public Date getCreatedDate() {
        return createdDate;
    }
    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }
    public Date getLastModifiedDate() {
        return lastModifiedDate;
    }
    public void setLastModifiedDate(Date lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

    
}
