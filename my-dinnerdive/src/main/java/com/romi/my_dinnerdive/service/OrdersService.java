package com.romi.my_dinnerdive.service;

import com.romi.my_dinnerdive.dto.CreateOrderRequest;

/** 購物車服務介面，定義與點餐相關的業務邏輯 */
public interface OrdersService {
    
    /** 創建訂單 */
    Integer createOrder(Integer userId,CreateOrderRequest createOrderRequest);
}
