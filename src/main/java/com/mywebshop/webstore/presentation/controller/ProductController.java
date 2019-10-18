package com.mywebshop.webstore.presentation.controller;

import com.mywebshop.webstore.domain.Product;
import com.mywebshop.webstore.service.impl.ProductServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ProductController {

    @Autowired
    ProductServiceImpl productServiceImpl;

    @RequestMapping("/products")
    public String listAllProducts(Model model) {


        model.addAttribute("productlist", productServiceImpl.finAllProducts());

        return "products";
    }


    @RequestMapping("/insertproduct")
    public String insertProduct(@ModelAttribute Product product) {

        productServiceImpl.insertProduct(product);

        return "redirect:/productlist";
    }


}
