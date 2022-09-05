package com.defaultarchmicroservice.controller;


import com.defaultarchmicroservice.controller.form.ProductForm;
import com.defaultarchmicroservice.mocks.ProductDtoMock;
import com.defaultarchmicroservice.mocks.ProductFormMock;
import com.defaultarchmicroservice.service.ProductService;
import com.google.gson.Gson;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ProductController.class)
@ActiveProfiles("test")
public class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductService service;

    @Test
    public void ShouldReturnObjectAfterSavePost() throws Exception {
        when(service.save(any(ProductForm.class))).thenReturn(ProductDtoMock.get());

        this.mockMvc.perform(post("/product")
                        .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)
                        .content(new Gson().toJson(ProductFormMock.get())))
                        .andExpect(status().isCreated())
                        .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1));
    }

}
