package com.mywebshop.webstore.domain.repository;

import com.mywebshop.webstore.domain.Product;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository {


    List<Product> findAll();

    Optional<Product> findbyID(String id);

    List<Product> findByCategory(String category);

    Boolean save(Product product);

    void updateStock(String productId, long noOfUnits);


}
