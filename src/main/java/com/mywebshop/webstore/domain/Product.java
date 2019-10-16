package com.mywebshop.webstore.domain;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class Product {

    private String productId;
    private String name;
    private BigDecimal unitPrice;
    private String description;
    private String manufacturer;
    private String category;
    private long unitsInStock;
    private long unitsInOrder;
    private boolean discontinued;
    private String condition;


}
