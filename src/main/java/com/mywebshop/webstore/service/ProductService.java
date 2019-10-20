package com.mywebshop.webstore.service;

import com.mywebshop.webstore.domain.Product;

import java.util.List;

public interface ProductService {

    List<Product> finAllProducts();

    boolean insertProduct(Product product);

    void updateStock();


}
