package com.mywebshop.webstore.domain.repository.impl;

import com.mywebshop.webstore.domain.Customer;
import com.mywebshop.webstore.domain.repository.CustomerRepository;
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
public class SpringJdbcCustomerRepositoryImpl implements CustomerRepository {

    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;


    @Override
    public List<Customer> findAll() {

        String query = "SELECT * FROM CUSTOMERS";

        Map<String, Object> params = new HashMap<>();

        List<Customer> result = jdbcTemplate.query(query, params, new SpringJdbcCustomerRepositoryImpl.CustomerMapper());

        return result;
    }

    @Override //TODO:needs implementation
    public Optional<Customer> findOne(Integer id) {
        return Optional.empty();
    }

    @Override
    public Boolean save(Customer customer) {

        String query = "insert into customers (ID,NAME,ADDRESS,NO_OF_ORDERS_MADE)" +
                "values (:id,:name,:address,:noOfOrdersmade)";

        Map<String, Object> params = new HashMap<>();
        params.put("id", customer.getId());
        params.put("name", customer.getName());
        params.put("address", customer.getAddress());
        params.put("noOfOrdersmade", customer.getNoOfOrdersMade());

        try {

            jdbcTemplate.update(query, params);
            return Boolean.TRUE;

        } catch (Exception e) {

            return Boolean.FALSE;
        }

    }

    private static final class CustomerMapper implements RowMapper<Customer> {

        public Customer mapRow(ResultSet resultSet, int rowNum) throws SQLException {

            Customer customer = new Customer();
            customer.setId(resultSet.getInt("ID"));
            customer.setName(resultSet.getString("NAME"));
            customer.setAddress(resultSet.getString("ADDRESS"));
            customer.setNoOfOrdersMade(resultSet.getInt("NO_OF_ORDERS_MADE"));
            return customer;


        }
    }
}
