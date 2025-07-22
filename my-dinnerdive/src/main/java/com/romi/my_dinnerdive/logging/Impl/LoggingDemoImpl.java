package com.romi.my_dinnerdive.logging.Impl;

import java.util.logging.*;

import org.springframework.stereotype.Component;
import com.romi.my_dinnerdive.logging.LoggingDemo;
import com.romi.my_dinnerdive.logging.formatter.MyCustomFormatter;

@Component
public class LoggingDemoImpl implements LoggingDemo{

    public Logger printMainLog() {
        Logger logger = Logger.getLogger("Main");
        LoggerSettings(logger);
        return logger;
    }

    public Logger printRandomRestaurantLog() {
        Logger logger = Logger.getLogger("RandomRestaurant");
        LoggerSettings(logger);
        return logger;
    }

    public Logger printUserLog() {
        Logger logger = Logger.getLogger("User");
        LoggerSettings(logger);
        return logger;
    }

    /**
     * 共用 logger 設定方法，若該 logger 尚未設定 handler，則：
     * <p>
     * 1. 關閉預設父處理器，避免重複輸出
     * <p>
     * 2. 設定等級為 Level.ALL（顯示所有等級的 log）
     * <p>
     * 3. 加入 ConsoleHandler 並套用自定 formatter（MyCustomFormatter）
     * 
     * @param logger 要初始化設定的 Logger 物件
     */
    private void LoggerSettings(Logger logger) {
        if (logger.getHandlers().length == 0){
            // 避免重複輸出
            logger.setUseParentHandlers(false);
            // 顯示所有等級的 log
            logger.setLevel(Level.ALL);

            ConsoleHandler consoleHandler = new ConsoleHandler();
            // handler 也設為 ALL
            consoleHandler.setLevel(Level.ALL);

            // 套用自定格式
            consoleHandler.setFormatter(new MyCustomFormatter());

            // 將處理器加進 logger
            logger.addHandler(consoleHandler);
        }
    }
}
