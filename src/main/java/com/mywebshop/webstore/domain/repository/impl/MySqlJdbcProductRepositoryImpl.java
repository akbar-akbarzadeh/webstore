package com.mywebshop.webstore.domain.repository.impl;

import com.mywebshop.webstore.domain.Product;
import com.mywebshop.webstore.domain.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class MySqlJdbcProductRepositoryImpl implements ProductRepository {

    List<Product> productList;

    @Autowired
    private DataSource mySqlDatasource;

    @Override
    public List<Product> findAll() {

        try {
            Connection connection = mySqlDatasource.getConnection();
            String query = "Select * from Produc";
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Product product = new Product();
            }
            return null;

        } catch (SQLException e) {
            e.printStackTrace();
            return null;

        }
    }
}
