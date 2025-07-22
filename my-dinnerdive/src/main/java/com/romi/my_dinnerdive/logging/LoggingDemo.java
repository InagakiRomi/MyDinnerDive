package com.romi.my_dinnerdive.logging;

import java.util.logging.Logger;

/** 顯示log的相關訊息 */
public interface LoggingDemo {
    
    /** 剛啟動時用的 logger */
    Logger printMainLog();

    /** 提供隨機餐廳邏輯用的 logger */
    Logger printRandomRestaurantLog();

    
    /** 提供帳號相關邏輯用的 logger */
    public Logger printUserLog();
}