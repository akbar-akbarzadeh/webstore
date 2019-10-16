package com.mywebshop.webstore.config.db;

import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Description;

import javax.sql.DataSource;


@Configuration
public class RootAppContextDataSourceConfiguration {

    @Bean
    @Description("getting a DataSource using DataSourceBuilder")
    public DataSource mySqlDatasource() {

        return DataSourceBuilder.create()
                .driverClassName("com.mysql.cj.jdbc.Driver")
                .url("jdbc:mysql://localhost:3306/mydb")
                .username("root")
                .password("maskhare")
                .build();

    }


    /*@Bean //needs configuring the context like user,pass etc
    @Description("getting a DataSource using JNDI ")
    public DataSource getJndiDataSource() {

        String DATASOURCE_CONTEXT = "java:comp/env/jdbc/mydb";
        DataSource dataSource = null;

        try {
            Context initialContext = new InitialContext();
            DataSource datasource = (DataSource) initialContext.lookup(DATASOURCE_CONTEXT);

        } catch (NamingException ex) {

            ex.printStackTrace();

        }// need to be fixed, data source always returns null
        return dataSource;
    }*/

}


