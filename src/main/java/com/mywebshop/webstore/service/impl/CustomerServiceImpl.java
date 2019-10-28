package com.mywebshop.webstore.service.impl;

import com.mywebshop.webstore.domain.Customer;
import com.mywebshop.webstore.domain.repository.CustomerRepository;
import com.mywebshop.webstore.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    CustomerRepository customerRepository;

    @Override
    public List<Customer> findAllCustomers() {
        return customerRepository.findAll();
    }

    @Override
    public Boolean addCustomer(Customer customer) {
        return customerRepository.save(customer);
    }
}
