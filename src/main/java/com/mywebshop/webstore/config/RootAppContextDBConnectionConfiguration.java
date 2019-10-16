package com.mywebshop.webstore.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Configuration
public class RootAppContextDBConnectionConfiguration {

    @Bean
    public Connection getJndiConnection() {

        String DATASOURCE_CONTEXT = "java:comp/env/jdbc/blah";
        Connection result = null;

        try {
            Context initialContext = new InitialContext();
            DataSource datasource = (DataSource) initialContext.lookup(DATASOURCE_CONTEXT);

            if (datasource != null) {
                result = datasource.getConnection();
            } else {
                System.out.println("Failed to lookup datasource.");

            }
        } catch (NamingException ex) {
            System.out.println("Cannot get connection: " + ex);
        } catch (SQLException ex) {
            System.out.println("Cannot get connection: " + ex);
        }
        return result;
    }

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
