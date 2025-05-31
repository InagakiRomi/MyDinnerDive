package com.romi.my_dinnerdive.logging.Impl;

import java.util.logging.ConsoleHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.stereotype.Component;
import com.romi.my_dinnerdive.logging.LoggingDemo;

@Component
public class LoggingDemoImpl implements LoggingDemo{
        
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
            logger.addHandler(consoleHandler);
        }
    }
}
