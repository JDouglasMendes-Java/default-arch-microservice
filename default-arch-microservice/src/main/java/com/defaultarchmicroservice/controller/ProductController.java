package com.defaultarchmicroservice.controller;

import com.defaultarchmicroservice.controller.dto.PageList;
import com.defaultarchmicroservice.controller.dto.ProductDto;
import com.defaultarchmicroservice.controller.form.ProductForm;
import com.defaultarchmicroservice.controller.form.ProductUpdateForm;
import com.defaultarchmicroservice.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Optional;

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

    @GetMapping("/{id}")
    public ResponseEntity<ProductDto> getById(@PathVariable Long id) {
        Optional<ProductDto> product = service.get(id);
        return product
                .map(productDto -> ResponseEntity.ok().body(productDto))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NO_CONTENT).body(null));

    }

    @GetMapping
    public PageList<List<ProductDto>> getAll(@PageableDefault(size = 10) Pageable pagination){
        return  service.getAll(pagination);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductDto> put(@RequestBody ProductUpdateForm form) {
        return  service.get(form.getId())
                .map(x -> ResponseEntity.ok().body(service.update(form)))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_MODIFIED).body(null));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable Long id) {
        return  service.get(id)
                .map(x -> ResponseEntity.ok().body(service.delete(id)))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).body(null));
    }
}
