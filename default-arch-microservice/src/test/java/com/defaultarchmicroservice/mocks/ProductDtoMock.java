package com.defaultarchmicroservice.mocks;

import com.defaultarchmicroservice.controller.dto.ProductDto;

public class ProductDtoMock {

    public static ProductDto get() {
        return ProductDto.builder().value(100.0).id(1L).name("Name Product").build();
    }
}
