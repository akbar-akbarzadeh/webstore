package com.mywebshop.webstore.service.impl;

import com.mywebshop.webstore.domain.Product;
import com.mywebshop.webstore.domain.repository.ProductRepository;
import com.mywebshop.webstore.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    @Qualifier("mySqlJdbcProductRepositoryImpl")
    private ProductRepository productRepository;

    public List<Product> finAllProducts() {

        return productRepository.findAll();
    }

    @Override
    public boolean insertProduct(Product product) {
        return productRepository.save(product);
    }


}
