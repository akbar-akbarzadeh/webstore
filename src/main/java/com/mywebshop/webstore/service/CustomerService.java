package com.mywebshop.webstore.service;

import com.mywebshop.webstore.domain.Customer;

import java.util.List;

public interface CustomerService {

    List<Customer> findAllCustomers();
}
