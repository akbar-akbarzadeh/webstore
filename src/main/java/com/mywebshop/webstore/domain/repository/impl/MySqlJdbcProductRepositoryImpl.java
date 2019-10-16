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
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class MySqlJdbcProductRepositoryImpl implements ProductRepository {


    @Autowired
    private DataSource mySqlDatasource;

    //private Connection connection;
    private List<Product> productList;


//constuctor throws exception, need to be fixed
/*
    public MySqlJdbcProductRepositoryImpl() throws SQLException{

         connection = mySqlDatasource.getConnection();

    }*/


    @Override
    public List<Product> findAll() {

        String query = "Select * from Products";

        try {

            Connection connection = mySqlDatasource.getConnection();
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();
            productList = new ArrayList<>();

            while (resultSet.next()) {

                Product product = new Product();
                product.setProductId(resultSet.getString("ID"));
                product.setName(resultSet.getString("NAME"));
                product.setDescription(resultSet.getString("DESCRIPTION"));
                product.setUnitPrice(resultSet.getBigDecimal("UNIT_PRICE"));
                product.setManufacturer(resultSet.getString("MANUFACTURER"));
                product.setCategory(resultSet.getString("CATEGORY"));
                product.setCondition(resultSet.getString("CONDITIONS"));
                product.setUnitsInStock(resultSet.getLong("UNITS_IN_STOCK"));
                product.setUnitsInOrder(resultSet.getLong("UNITS_IN_ORDER"));
                product.setDiscontinued(resultSet.getBoolean("DISCONTINUED"));
                productList.add(product);
            }

            return productList;

        } catch (SQLException e) {
            e.printStackTrace();
            return null;

        }
    }


    @Override
    public Optional<Product> findbyID(String id) {
        return Optional.empty();
    }
}


   /* public List<Person> select() throws Exception {
        preparedStatement = connection.prepareStatement("select * from person");
        ResultSet resultSet = preparedStatement.executeQuery();
        List<Person> personList = new ArrayList<>();
        while (resultSet.next()) {
            personList.add(new Person(resultSet.getLong("id"), resultSet.getString("name"), resultSet.getString("family")));
        }
        return personList;
    }*/