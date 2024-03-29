package com.mywebshop.webstore.domain.repository.impl;

import com.mywebshop.webstore.domain.Product;
import com.mywebshop.webstore.domain.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public class SpringJdbcProductRepositoryImpl implements ProductRepository {

    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;


    @Override
    public List<Product> findAll() {

        Map<String, Object> params = new HashMap<String, Object>();

        List<Product> result = jdbcTemplate.query("SELECT * FROM products", params, new ProductMapper());

        return result;

    }

    @Override
    public void updateStock(String productId, long noOfUnits) {

        String query = "update products set units_in_stock=:unitsInStock where id=:id";
        Map<String, Object> queryParams = new HashMap<>();
        queryParams.put("unitsInStock", noOfUnits);
        queryParams.put("id", productId);
        jdbcTemplate.update(query, queryParams);
        System.out.println("update completed");
    }

    //TODO : needs implementation
    @Override
    public Optional<Product> findbyID(String id) {
        return Optional.empty();
    }

    //TODO : needs implememntation
    @Override
    public Boolean save(Product product) {
        return null;
    }

    private static final class ProductMapper implements RowMapper<Product> {

        public Product mapRow(ResultSet resultSet, int rowNum) throws SQLException {

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
            return product;
        }
    }





}

