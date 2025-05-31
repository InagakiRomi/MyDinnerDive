package com.romi.my_dinnerdive.logging;

import java.util.logging.ConsoleHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.stereotype.Component;

/**
 * LoggingDemo 類別用於顯示log的相關訊息。
 */
@Component
public class LoggingDemo {
    
    /**
     * 打印隨機抽取餐廳的log。
     */
    public Logger printRandomRestaurantLog() {
        Logger logger = Logger.getLogger("RandomRestaurant");

        if (logger.getHandlers().length == 0){
            logger.setUseParentHandlers(false); //避免重複Log
            logger.setLevel(Level.ALL);

            ConsoleHandler consoleHandler = new ConsoleHandler();
            consoleHandler.setLevel(Level.ALL);
            logger.addHandler(consoleHandler);
        }

        return logger;
    }
}