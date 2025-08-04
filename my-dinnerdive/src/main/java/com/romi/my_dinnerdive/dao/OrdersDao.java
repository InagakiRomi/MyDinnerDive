package com.romi.my_dinnerdive.dao;

/** 定義購物車所有與資料庫互動的方法 */
public interface OrdersDao {

    /** 創建訂單 */
    Integer createOrder(Integer userId, Integer totalAmount);
}
