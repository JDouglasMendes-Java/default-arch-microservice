package com.defaultarchmicroservice.mocks;

import com.defaultarchmicroservice.controller.form.ProductUpdateForm;

public class ProductUpdateFormMock {
    public static ProductUpdateForm get() {
        return ProductUpdateForm.builder()
                .id(1L)
                .value(100.0)
                .name("Name Product")
                .build();
    }
}
