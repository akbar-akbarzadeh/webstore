package com.mywebshop.webstore.config;

import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;


@Configuration
public class RootAppContextDataSourceConfiguration {

    @Bean
    public DataSource mySqlDatasource() {

        return DataSourceBuilder.create()
                .driverClassName("com.mysql.cj.jdbc.Driver")
                .url("jdbc:mysql://localhost:3306/mydb")
                .username("root")
                .password("maskhare")
                .build();

    }


}


