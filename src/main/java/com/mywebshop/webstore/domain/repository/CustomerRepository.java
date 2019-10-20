package com.mywebshop.webstore.domain.repository;

import com.mywebshop.webstore.domain.Customer;

import java.util.List;
import java.util.Optional;

public interface CustomerRepository {

    List<Customer> findAll();

    Optional<Customer> findOne(Integer id);
}
