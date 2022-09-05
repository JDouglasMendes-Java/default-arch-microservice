package com.defaultarchmicroservice.service;


import com.defaultarchmicroservice.controller.dto.PageList;
import com.defaultarchmicroservice.controller.dto.ProductDto;
import com.defaultarchmicroservice.mocks.ProductDtoMock;
import com.defaultarchmicroservice.mocks.ProductFormMock;
import com.defaultarchmicroservice.mocks.ProductMock;
import com.defaultarchmicroservice.mocks.ProductUpdateFormMock;
import com.defaultarchmicroservice.model.Product;
import com.defaultarchmicroservice.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@DataJpaTest
public class ProductServiceTest {
    @Mock
    ProductRepository repository;

    @Mock
    ModelMapper mapper;

    @InjectMocks
    ProductService service;

    @Test
    public void shouldAddNewProductWithSuccess() {
        when(repository.save(any(Product.class))).thenReturn(ProductMock.get());
        when(mapper.map(any(), eq(Product.class))).thenReturn(ProductMock.get());
        when(mapper.map(any(),eq(ProductDto.class))).thenReturn(ProductDtoMock.get());

        ProductDto response = service.save(ProductFormMock.get());
        ProductDto productDto = ProductDtoMock.get();
        assertEquals(productDto , response);
    }

    @Test
    public void shouldUpdateProductWithSuccess() {
        when(repository.save(any(Product.class))).thenReturn(ProductMock.get());
        when(mapper.map(any(), eq(Product.class))).thenReturn(ProductMock.get());
        when(mapper.map(any(),eq(ProductDto.class))).thenReturn(ProductDtoMock.get());

        ProductDto response = service.update(ProductUpdateFormMock.get());
        ProductDto productDto = ProductDtoMock.get();
        assertEquals(productDto , response);
    }

    @Test
    public  void getAllItemsWithSuccess() {
        when(repository.findAll(any(Pageable.class))).thenReturn(Page.empty());

        PageList<List<ProductDto>> pageList = service.getAll(Pageable.ofSize(1));

        assertEquals(0, pageList.getAmountItems());
        assertEquals(1, pageList.getTotalPages());
        assertEquals(0, pageList.getItems().size());
    }

    @Test
    public void getWithSuccess() {
        when(repository.findById(eq(1L))).thenReturn(Optional.of(ProductMock.get()));
        when(mapper.map(any(), eq(ProductDto.class))).thenReturn(ProductDtoMock.get());

        Optional<ProductDto> productDto = service.get(1L);
        ProductDto result = productDto.orElseThrow();

        assertEquals(1L, result.getId());
        assertEquals("Name Product", result.getName());
        assertEquals(100.0, result.getValue());
    }

    @Test
    public void deleteWithSuccess() {
        service.delete(1L);
        verify(repository).deleteById(any());
    }
}
