package com.defaultarchmicroservice.mocks;

import com.defaultarchmicroservice.model.Product;

public class ProductMock {

    public  static Product get() {
        return  Product.builder().name("Name Product").value(100.0).id(1L).build();
    }
}
