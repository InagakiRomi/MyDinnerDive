package com.romi.my_dinnerdive.config;

import java.util.logging.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import com.romi.my_dinnerdive.logging.LoggingDemo;

import javax.sql.DataSource;

@Configuration
public class DataSourceConfig {

    @Value("${spring.datasource.url}")
    private String mysqlUrl;

    @Value("${spring.datasource.username}")
    private String mysqlUsername;

    @Value("${spring.datasource.password}")
    private String mysqlPassword;

    @Autowired
    private LoggingDemo loggingDemo;

    @Bean
    @Primary
    public DataSource dataSource() {
        Logger logger = loggingDemo.printDataSourceConfig();
        try {
            DriverManagerDataSource mysql = new DriverManagerDataSource();
            mysql.setDriverClassName("com.mysql.cj.jdbc.Driver");
            mysql.setUrl(mysqlUrl);
            mysql.setUsername(mysqlUsername);
            mysql.setPassword(mysqlPassword);

            mysql.getConnection().close();
            logger.log(Level.INFO,"成功連線 MySQL，使用 MySQL 資料庫");
            return mysql;
        } catch (Exception connection) {
            logger.log(Level.INFO,"無法連線 MySQL，自動切換至 H2：" + connection.getMessage());

            DriverManagerDataSource h2 = new DriverManagerDataSource();
            h2.setDriverClassName("org.h2.Driver");
            h2.setUrl("jdbc:h2:mem:testdb;DB_CLOSE_DELAY=-1");
            h2.setUsername("sa");
            h2.setPassword("");
            return h2;
        }
    }

    @Bean
    public NamedParameterJdbcTemplate namedParameterJdbcTemplate(DataSource dataSource) {
        return new NamedParameterJdbcTemplate(dataSource);
    }
}