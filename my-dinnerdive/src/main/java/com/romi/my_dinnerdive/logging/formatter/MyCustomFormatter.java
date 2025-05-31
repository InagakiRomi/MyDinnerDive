package com.romi.my_dinnerdive.logging.formatter;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.*;

public class MyCustomFormatter extends Formatter{
    
    private static final String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";
    private final SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT);

    // 色彩輸出碼
    private static final String RESET = "\u001B[0m";
    private static final String RED = "\u001B[31m";
    private static final String GREEN = "\u001B[32m";
    private static final String YELLOW = "\u001B[33m";
    private static final String BLUE = "\u001B[34m";
    private static final String PURPLE = "\u001B[35m";
    private static final String CYAN = "\u001B[36m";
    private static final String WHITE = "\u001B[37m";

    @Override
    public String format(LogRecord record) {
        StringBuilder sb = new StringBuilder();
        String levelColor = getColorForLevel(record.getLevel());

        sb.append(BLUE)
          .append("[")
          .append(dateFormat.format(new Date(record.getMillis())))
          .append("]")
          .append(RESET);

        sb.append(" ");
        sb.append(levelColor)
          .append("[")
          .append(record.getLevel().getName())
          .append("]")
          .append(RESET);

        sb.append(" ");
        sb.append(PURPLE)
          .append("[")
          .append(record.getLoggerName())
          .append("]")
          .append(RESET);

        sb.append(" ");
        sb.append(WHITE)
          .append(formatMessage(record))
          .append(RESET)
          .append("\n");

        return sb.toString();
    }

    private String getColorForLevel(Level level) {
        if (level == Level.SEVERE) return RED;
        if (level == Level.WARNING) return YELLOW;
        if (level == Level.INFO) return GREEN;
        if (level == Level.CONFIG) return CYAN;
        if (level == Level.FINE || level == Level.FINER || level == Level.FINEST) return WHITE;
        return WHITE;
    }
}
