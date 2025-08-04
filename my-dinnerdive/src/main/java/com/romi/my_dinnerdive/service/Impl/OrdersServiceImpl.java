package com.romi.my_dinnerdive.service.Impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import com.romi.my_dinnerdive.dao.OrdersDao;
import com.romi.my_dinnerdive.dto.BuyItem;
import com.romi.my_dinnerdive.dto.CreateOrderRequest;
import com.romi.my_dinnerdive.model.OrderItem;
import com.romi.my_dinnerdive.model.Restaurant;
import com.romi.my_dinnerdive.model.User;

import jakarta.transaction.Transactional;

public class OrdersServiceImpl {
    
    @Autowired
    private OrdersDao ordersDao;

    @Transactional
    @Override
    public Integer createOrder(Integer userId, CreateOrderRequest createOrderRequest) { 
        // 檢查 user 是否存在
        User user = userDao.getUserById(userId);

        if (user == null) {
            log.warn("該 userId {} 不存在", userId);
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

        
        int totalAmount = 0;
        List<OrderItem> orderItemList = new ArrayList<>();

        for (BuyItem buyItem : createOrderRequest.getBuyItemList()) {
            Restaurant restaurant = restaurantDao.getRestaurantById(buyItem.getDiseId());

            // 檢查 product 是否存在，庫存是否足夠
            if (restaurant == null) {
                log.warn("商品 {} 不存在", buyItem.getDiseId());
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
            } else if (restaurant.getStock() < buyItem.getQuantity()) {
                log.warn("商品 {} 庫存數量不足，無法購買。剩餘庫存 {}，欲購買數量 {}",
                        buyItem.getDiseId(), restaurant.getStock(), buyItem.getQuantity());
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
            }

            // 扣除商品庫存
            ordersDao.updateStock(restaurant.getDiseId(), restaurant.getStock() - buyItem.getQuantity());
                
            // 計算總價錢
            int amount = buyItem.getQuantity() * restaurant.getPrice();
            totalAmount = totalAmount + amount;

            // 轉換 BuyItem to OrderItem
            OrderItem orderItem = new OrderItem();
            orderItem.setDiseId(buyItem.getDiseId());
            orderItem.setQuantity(buyItem.getQuantity());
            orderItem.setAmount(amount);

            orderItemList.add(orderItem);
        }
    
        // 創建訂單
        Integer orderId = ordersDao.createOrder(userId, totalAmount);

        ordersDao.createOrderItems(orderId, orderItemList);

        return orderId;
    }
}
