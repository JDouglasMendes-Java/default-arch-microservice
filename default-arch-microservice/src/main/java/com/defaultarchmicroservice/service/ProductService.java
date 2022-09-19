package com.defaultarchmicroservice.service;

import com.defaultarchmicroservice.controller.dto.PageList;
import com.defaultarchmicroservice.controller.dto.ProductDto;
import com.defaultarchmicroservice.controller.form.ProductForm;
import com.defaultarchmicroservice.controller.form.ProductUpdateForm;
import com.defaultarchmicroservice.model.Product;
import com.defaultarchmicroservice.repository.ProductRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
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

    @Caching(evict = {
            @CacheEvict(value = "product", allEntries = true),
            @CacheEvict(value = "products", allEntries = true)
    })
    public ProductDto save(ProductForm form) {
        return mapper.map(repository
                        .save(mapper
                                .map(form, Product.class)),
                        ProductDto.class);
    }

    @Caching(evict = {
            @CacheEvict(value = "product", allEntries = true),
            @CacheEvict(value = "products", allEntries = true)
    })
    public ProductDto update(ProductUpdateForm form) {
        return mapper.map(repository
                        .save(mapper
                                .map(form, Product.class)),
                            ProductDto.class);
    }

    @Caching(evict = {
            @CacheEvict(value = "product", allEntries = true),
            @CacheEvict(value = "products", allEntries = true)
    })
    public boolean delete(Long id) {
        repository.deleteById(id);
        return true;
    }

    @Cacheable(value = "products")
    public PageList<List<ProductDto>> getAll(Pageable pageable) {
        Page<Product> page = repository.findAll(pageable);
        return new PageList<>(page.getNumber(),
                page.getTotalPages(),
                page.get()
                        .map(x -> mapper
                                .map(x, ProductDto.class))
                        .collect(Collectors.toList()));
    }

    @Cacheable(value = "product", key = "#id")
    public Optional<ProductDto> get(Long id) {
        Optional<Product> productOptional = repository.findById(id);
        return productOptional.map(product -> mapper.map(product, ProductDto.class));
    }
}
