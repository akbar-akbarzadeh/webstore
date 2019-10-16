package com.mywebshop.webstore.domain.repository;

import com.mywebshop.webstore.domain.Product;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository {


    List<Product> findAll();
}
