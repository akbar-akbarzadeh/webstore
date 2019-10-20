package com.mywebshop.webstore.domain;

import lombok.Data;

@Data
public class Customer {

    private Integer Id;
    private String name;
    private String address;
    private Integer noOfOrdersMade;
}
