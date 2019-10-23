package com.mywebshop.webstore.service;

import com.mywebshop.webstore.domain.Product;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface ProductService {

    List<Product> finAllProducts();

    boolean insertProduct(Product product);

    void updateStock();

    Optional<Product> findProductById(String productId);

    List<Product> findProductsByCategory(String category);

    List<Product> findProductsByFilterParams(Map<String, List<String>> params);



}
