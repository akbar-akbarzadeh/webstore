package com.mywebshop.webstore.domain.repository.impl;

import com.mywebshop.webstore.domain.Product;
import com.mywebshop.webstore.domain.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

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


    /*Product RowMapper*/

    @Override
    public List<Product> findByParams(Map<String, List<String>> params) {

        String query = "select * from products where manufacturer in (:brands) or category in (:categories)";

        List<Product> result = jdbcTemplate.query(query, params, new ProductMapper());


        return result;

    }



    @Override
    public List<Product> findByCategory(String category) {

        String query = "Select * from products where category=:category";
        Map<String,Object> param = new HashMap<>();
        param.put("category",category);
        List<Product> result = jdbcTemplate.query(query,param,new SpringJdbcProductRepositoryImpl.ProductMapper());
        return result;
    }


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

    }

    //TODO : needs implementation
    @Override
    public Optional<Product> findbyID(String id) {

        String query = "select * from products where id=:productId";
        Map<String, Object> params = new HashMap<>();
        params.put("productId", id);
        Optional<Product> product = Optional.of(jdbcTemplate.query(query, params, new ProductMapper()).get(0));

        return product;
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


    @Override
    @Transactional
    public Boolean save(Product product) {

        String query = "INSERT INTO PRODUCTS (ID, "
                + "NAME,"
                + "DESCRIPTION,"
                + "UNIT_PRICE,"
                + "MANUFACTURER,"
                + "CATEGORY,"
                + "CONDITIONS,"
                + "UNITS_IN_STOCK,"
                + "UNITS_IN_ORDER,"
                + "DISCONTINUED) "
                + "VALUES (:id, :name, :desc, :price,:manufacturer, :category, :condition, :inStock,:inOrder, :discontinued)";

        /*String query = "INSERT INTO PRODUCTS "
                + "(ID, "
                + "NAME,"
                + "DESCRIPTION,"
                + "UNIT_PRICE,"
                + "MANUFACTURER,"
                + "CATEGORY,"
                + "CONDITION,"
                + "UNITS_IN_STOCK,"
                + "UNITS_IN_ORDER,"
                + "DISCONTINUED) "
                + "VALUES (:id, :name, :desc, :price,:manufacturer, :category, :condition, :inStock,:inOrder, :discontinued)";*/

        Map<String, Object> params = new HashMap<>();

        params.put("id", product.getProductId());
        params.put("name", product.getName());
        params.put("desc", product.getDescription());
        params.put("price", product.getUnitPrice());
        params.put("manufacturer", product.getManufacturer());
        params.put("category", product.getCategory());
        params.put("condition", product.getCondition());
        params.put("inStock", product.getUnitsInStock());
        params.put("inOrder", product.getUnitsInOrder());
        params.put("discontinued", (product.getDiscontinued()) ? 1 : 0);

        try {
            jdbcTemplate.update(query, params);
            return Boolean.TRUE;

        } catch (Exception e) {
            e.printStackTrace();
            return Boolean.FALSE;
        }

    }
}

