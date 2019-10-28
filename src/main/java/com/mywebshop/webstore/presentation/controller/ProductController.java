package com.mywebshop.webstore.presentation.controller;

import com.mywebshop.webstore.domain.Product;
import com.mywebshop.webstore.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/market")
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

        return "redirect:/market/productlist"; // should be mapped to correct URL
    }

    @RequestMapping("/products/updatestock")

    public String updateStock(Model model) {
        productService.updateStock();
        return "redirect:/market/products"; // should be mapped to correct URL


        }

    @RequestMapping("/products/{category}") //using URI template
    public ModelAndView getProdcustByCategory(@PathVariable("category") String productCategory)

    { //using @Pathvariable

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("productlist",productService.findProductsByCategory(productCategory));
        modelAndView.setViewName("products");
        return  modelAndView; //return modelAndView object

    }

    @RequestMapping("/products/filter/{params}")

    //http://localhost:8080/market/products/filter/params;brands=Google,Dell;categories=Tablet,Laptop
    //http://localhost:8080/market/products/filter/params;brands=Google;brands=Dell;categories=Tablet;categories=Laptop

    public ModelAndView getProductsByBrandAndCategory(@MatrixVariable(pathVar = "params") Map<String, List<String>> params) {

        ModelAndView modelAndView = new ModelAndView();

        modelAndView.addObject("productlist", productService.findProductsByFilterParams(params));
        modelAndView.setViewName("products");

        return modelAndView;


    }

    //an example of 2 segments of matrix value
    @RequestMapping("/products/filter/{params}/{prices}")

    //http://localhost:8080/market/products/filter/params;brands=Google,Dell;categories=Tablet,Laptop/price;price=500,300

    public ModelAndView getProductsByBrandAndCategoryAndPrice
    (@MatrixVariable(pathVar = "params") Map<String, List<String>> params,
     @MatrixVariable(pathVar = "prices") Map<String, List<String>> prices) {

        ModelAndView modelAndView = new ModelAndView();

        modelAndView.addObject("productlist", productService.findProductsByFilterParams(params));
        modelAndView.setViewName("products");

        return modelAndView;


    }

    @RequestMapping("/product")
    //http://localhost:8080/market/product?productid=P1234
    public ModelAndView getProductsById(@RequestParam("productid") String productID) {


        System.out.println(productService.findProductById(productID).get());
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("product", productService.findProductById(productID).get());
        modelAndView.setViewName("product");

        return modelAndView;
    }

    @GetMapping("/products/add/showform")
    public String getAddNewProductForm(Model model) {

        Product product = new Product(); //instantiate the product and send it to view to fill it as ModelAttribute
        model.addAttribute("newProduct", product);
        // instead of above code we could write
        //public String getAddNewProductForm(@ModelAttribute("newProduct") Product product) {

        //Spring MVC will know that it should create an object of
        //Product and attach it to the model under the name product

        return "addProduct";
    }

    @PostMapping("/products/add/processform")
    public String processAddNewProductForm(@ModelAttribute("newProduct") Product product) {
        System.out.println(product.toString());
        if (productService.insertProduct(product)) {

            return "redirect:/market/products";
        } else {
            return "addProduct";
        }
    }
}
