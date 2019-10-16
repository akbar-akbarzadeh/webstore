package com.mywebshop.webstore.domain.repository.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class SpringJdbcProductRepositoryImpl {

    @Autowired
    NamedParameterJdbcTemplate jdbcTemplate;


}

