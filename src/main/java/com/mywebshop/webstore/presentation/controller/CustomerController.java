package com.mywebshop.webstore.presentation.controller;

import com.mywebshop.webstore.domain.Customer;
import com.mywebshop.webstore.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
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

    @GetMapping("/customers/add/form")
    public String addCustomerShowForm(@ModelAttribute("newCustomer") Customer newCustomer) {
        // since we use @ModelAttribute here, no need to instantiate Customer,Spring MVC will do it for us
        return "addcustomer";
    }

    @PostMapping("/customers/add/form")
    public String addCustomerProcessForm(@ModelAttribute("newCustomer") Customer newCustomer) {

        if (customerService.addCustomer(newCustomer)) {

            return "redirect:/customers";
        } else {
            return "addcustomer";
        }
    }

}
