package com.mywebshop.webstore.presentation.controller;

import com.mywebshop.webstore.domain.Product;
import com.mywebshop.webstore.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ProductController {

    @Autowired
    private ProductService productService;

    @RequestMapping("/products")
    public String listAllProducts(Model model) {


        model.addAttribute("productlist", productService.finAllProducts());

        return "products";
    }


    @RequestMapping("/insertproduct")
    public String insertProduct(@ModelAttribute Product product) {

        productService.insertProduct(product);

        return "redirect:/productlist";
    }

    @RequestMapping("/products/updatestock")

    public String updateStock(Model model) {
        productService.updateStock();
        return "redirect:/products";


    }


}
