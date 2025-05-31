package com.romi.my_dinnerdive.logging.formatter;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.*;

/**
 * 自定義日誌格式化器：MyCustomFormatter
 *
 * 此類別擴展了 java.util.logging.Formatter，提供自定義的日誌輸出格式。
 * 日誌將包含時間戳、日誌級別、記錄器名稱和日誌消息，並使用 ANSI 色彩碼進行彩色輸出。
 */
public class MyCustomFormatter extends Formatter{
    
    // 日期格式化字符串
    private static final String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";
    private final SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT);

    // 色彩輸出碼
    private static final String RESET = "\u001B[0m";
    private static final String RED = "\u001B[31m";
    private static final String GREEN = "\u001B[32m";
    private static final String YELLOW = "\u001B[33m";
    private static final String BLUE = "\u001B[34m";
    private static final String CYAN = "\u001B[36m";
    private static final String WHITE = "\u001B[37m";

    /**
     * 格式化日誌記錄。
     *
     * @param record 要格式化的日誌記錄
     * @return 格式化後的日誌字符串
     */
    @Override
    public String format(LogRecord record) {
        StringBuilder sb = new StringBuilder();
        String levelColor = getColorForLevel(record.getLevel());

        sb.append(WHITE)
          .append(dateFormat.format(new Date(record.getMillis())))
          .append(RESET);

        sb.append(" ");
        sb.append(levelColor)
          .append(record.getLevel().getName())
          .append(RESET);

        sb.append(" ");
        sb.append(YELLOW)
          .append("[")
          .append(record.getLoggerName())
          .append("]")
          .append(RESET);

        sb.append(" : ");
        sb.append(WHITE)
          .append(formatMessage(record))
          .append(RESET)
          .append("\n");

        return sb.toString();
    }

    /**
     * 根據日誌級別返回對應的 ANSI 色彩碼。
     *
     * @param level 日誌級別
     * @return 對應的色彩碼
     */
    private String getColorForLevel(Level level) {
        if (level == Level.SEVERE) return RED;
        if (level == Level.WARNING) return YELLOW;
        if (level == Level.INFO) return GREEN;
        if (level == Level.CONFIG) return CYAN;
        if (level == Level.FINE || level == Level.FINER || level == Level.FINEST) return BLUE;
        return WHITE;
    }
}
