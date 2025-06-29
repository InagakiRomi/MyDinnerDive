package com.romi.my_dinnerdive.logging;

import java.util.logging.Logger;

/**
 * LoggingDemo 類別用於顯示log的相關訊息。
 */
public interface LoggingDemo {
    
    /**
     * 打印執行應用程式log。
     */
    Logger printMainLog();

    /**
     * 打印啟動時撈資料庫的log。
     */
    Logger printDataSourceConfig();

    /**
     * 打印隨機抽取餐廳的log。
     */
    Logger printRandomRestaurantLog();

    
    /**
     * 帳號相關的log。
     */
    public Logger printUserLog();
}