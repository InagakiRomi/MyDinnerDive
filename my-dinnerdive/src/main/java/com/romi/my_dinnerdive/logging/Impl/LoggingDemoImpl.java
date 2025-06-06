package com.romi.my_dinnerdive.logging.Impl;

import java.util.logging.*;

import org.springframework.stereotype.Component;
import com.romi.my_dinnerdive.logging.LoggingDemo;
import com.romi.my_dinnerdive.logging.formatter.MyCustomFormatter;

@Component
public class LoggingDemoImpl implements LoggingDemo{

    /**
     * 打印執行應用程式log。
     */
    public Logger printMainLog() {
        Logger logger = Logger.getLogger("Main");
        LoggerSettings(logger);
        return logger;
    }
        
    /**
     * 打印啟動時撈資料庫的log。
     */
    public Logger printDataSourceConfig() {
        Logger logger = Logger.getLogger("DataSourceConfig");
        LoggerSettings(logger);
        return logger;
    }

    /**
     * 打印隨機抽取餐廳的log。
     */
    public Logger printRandomRestaurantLog() {
        Logger logger = Logger.getLogger("RandomRestaurant");
        LoggerSettings(logger);
        return logger;
    }

    /**
     * logger 共用設定方法。
     * 這個方法會檢查 logger 是否已經有 handler，如果沒有，則設定一個 ConsoleHandler 並設置其等級為 ALL。
     * 這樣可以避免重複添加 handler，並確保所有 log 都能被輸出到控制台。
     * @param logger 要設定的 Logger 物件
     */
    private void LoggerSettings(Logger logger) {
        if (logger.getHandlers().length == 0){
            logger.setUseParentHandlers(false); //避免重複Log
            logger.setLevel(Level.ALL);

            ConsoleHandler consoleHandler = new ConsoleHandler();
            consoleHandler.setLevel(Level.ALL);

            consoleHandler.setFormatter(new MyCustomFormatter());

            logger.addHandler(consoleHandler);
        }
    }
}
