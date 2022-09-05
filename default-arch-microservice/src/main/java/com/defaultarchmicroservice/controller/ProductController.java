package com.defaultarchmicroservice.controller;

import com.defaultarchmicroservice.controller.dto.ProductDto;
import com.defaultarchmicroservice.controller.form.ProductForm;
import com.defaultarchmicroservice.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
@RequestMapping("/product")
@AllArgsConstructor
public class ProductController {

    private ProductService service;

    @PostMapping
    public ResponseEntity<ProductDto> post(@RequestBody ProductForm form) {
        ProductDto productDto = service.save(form);
        return ResponseEntity
                    .created(URI.create(String.format("/product/%s", productDto.getId())))
                    .body(productDto);
    }

}
