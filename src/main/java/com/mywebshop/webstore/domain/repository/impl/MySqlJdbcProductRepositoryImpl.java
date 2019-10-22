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


//TODO: constuctor throws exception, need to be fixed
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


    @Override //todo: needs implementation
    public Optional<Product> findbyID(String id) {
        return Optional.empty();
    }

    @Override //todo: needs implementation
    public List<Product> findByCategory(String category) {
        return null;
    }

    @Override
    public Boolean save(Product product) {
        String query = "INSERT INTO PRODUCTS (ID,NAME,DESCRIPTION,UNIT_PRICE,MANUFACTURER,CATEGORY,CONDITION," +
                "UNITS_IN_STOCK,UNITS_IN_ORDER,DISCONTINUED)" + " VALUES (?, ?, ?,?,?,?,?,?,?,?)";

        try {
            Connection connection = mySqlDatasource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, product.getProductId());
            preparedStatement.setString(2, product.getName());
            preparedStatement.setString(3, product.getDescription());
            preparedStatement.setBigDecimal(4, product.getUnitPrice());
            preparedStatement.setString(5, product.getManufacturer());
            preparedStatement.setString(6, product.getCategory());
            preparedStatement.setString(7, product.getCondition());
            preparedStatement.setLong(8, product.getUnitsInStock());
            preparedStatement.setLong(9, product.getUnitsInOrder());
            preparedStatement.setBoolean(10, product.isDiscontinued());
            return preparedStatement.execute();

        } catch (SQLException ex) {

            System.out.println("Insert Failed");
            return Boolean.FALSE;
        }


    }

    @Override
    public void updateStock(String productId, long noOfUnits) {
        //TODO: needs implementation
    }
}


