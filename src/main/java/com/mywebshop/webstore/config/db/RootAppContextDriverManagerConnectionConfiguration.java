package com.mywebshop.webstore.config.db;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


@Configuration
public class RootAppContextDriverManagerConnectionConfiguration {



    @Bean
    public Connection getSimpleConnection() {

        String DB_CONN_STRING = "jdbc:mysql://localhost:3306/mydb";
        String DRIVER_CLASS_NAME = "com.mysql.cj.jdbc.Driver";
        String USER_NAME = "root";
        String PASSWORD = "maskhare";
        Connection result = null;

        try {
            Class.forName(DRIVER_CLASS_NAME).getDeclaredConstructor().newInstance();
        } catch (Exception ex) {
            System.out.println("Check classpath. Cannot load db driver: " + DRIVER_CLASS_NAME);
        }

        try {
            result = DriverManager.getConnection(DB_CONN_STRING, USER_NAME, PASSWORD);
        } catch (SQLException e) {
            System.out.println("Driver loaded, but cannot connect to db: " + DB_CONN_STRING);
        }
        return result;
    }
}
