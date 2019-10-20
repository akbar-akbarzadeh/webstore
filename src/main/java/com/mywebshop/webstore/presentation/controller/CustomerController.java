package com.mywebshop.webstore.presentation.controller;

import com.mywebshop.webstore.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CustomerController {

    @Autowired
    CustomerService customerService;

    @RequestMapping("/customers")
    public String listCustomers(Model model) {

        model.addAttribute("customerslist", customerService.findAllCustomers());

        return "customers";
    }
}
