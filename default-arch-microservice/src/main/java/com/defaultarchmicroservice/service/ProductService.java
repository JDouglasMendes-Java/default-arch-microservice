package com.defaultarchmicroservice.service;

import com.defaultarchmicroservice.controller.dto.PageList;
import com.defaultarchmicroservice.controller.dto.ProductDto;
import com.defaultarchmicroservice.controller.form.ProductForm;
import com.defaultarchmicroservice.controller.form.ProductUpdateForm;
import com.defaultarchmicroservice.model.Product;
import com.defaultarchmicroservice.repository.ProductRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductService {

    @Autowired
    ProductRepository repository;

    @Autowired
    ModelMapper mapper;

    public ProductDto save(ProductForm form) {
        return mapper.map(repository
                        .save(mapper
                                .map(form, Product.class)),
                        ProductDto.class);
    }

    public ProductDto update(ProductUpdateForm form) {
        return mapper.map(repository
                        .save(mapper
                                .map(form, Product.class)),
                            ProductDto.class);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

    public PageList<List<ProductDto>> getAll(Pageable pageable) {
        Page<Product> page = repository.findAll(pageable);
        return new PageList<>(page.getNumber(),
                page.getTotalPages(),
                page.get()
                        .map(x -> mapper
                                .map(x, ProductDto.class))
                        .collect(Collectors.toList()));
    }

    public Optional<ProductDto> get(Long id) {
        Optional<Product> productOptional = repository.findById(id);
        return productOptional.map(product -> mapper.map(product, ProductDto.class));
    }


}
