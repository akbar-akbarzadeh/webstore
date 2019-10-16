package com.mywebshop.webstore.presentation.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ProductController {
    @RequestMapping("/products")
    public String listProducts(Model model) {
        /*Product product = new Product("123","iPhone", new BigDecimal(5000));
        model.addAttribute("product",product);*/
        return "productlist";
    }

}
