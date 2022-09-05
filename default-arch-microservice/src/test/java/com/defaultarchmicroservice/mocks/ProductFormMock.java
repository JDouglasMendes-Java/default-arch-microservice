package com.defaultarchmicroservice.mocks;

import com.defaultarchmicroservice.controller.dto.ProductDto;
import com.defaultarchmicroservice.controller.form.ProductForm;

public class ProductFormMock {
    public static ProductForm get() {
        return ProductForm.builder().value(100.0).name("Name Product").build();
    }

}
