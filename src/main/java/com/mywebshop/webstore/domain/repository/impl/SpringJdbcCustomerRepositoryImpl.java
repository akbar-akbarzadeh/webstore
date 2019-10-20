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

    private static final class CustomerMapper implements RowMapper<Customer> {

        public Customer mapRow(ResultSet resultSet, int rowNum) throws SQLException {

            Customer customer = new Customer();
            customer.setId(resultSet.getInt("ID"));
            customer.setName(resultSet.getString("NAME"));
            customer.setAddress(resultSet.getString("ADDRESS"));
            customer.setNoOfOrdersMade(resultSet.getInt("noOfOrdersMade"));
            return customer;


        }
    }
}
