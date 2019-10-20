package com.mywebshop.webstore.service.impl;

import com.mywebshop.webstore.domain.Customer;
import com.mywebshop.webstore.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    CustomerService customerService;

    @Override
    public List<Customer> findAllCustomers() {
        return customerService.findAllCustomers();
    }
}
