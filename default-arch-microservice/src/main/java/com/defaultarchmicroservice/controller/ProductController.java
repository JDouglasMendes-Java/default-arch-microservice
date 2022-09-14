package com.defaultarchmicroservice.controller;

import com.defaultarchmicroservice.controller.dto.PageList;
import com.defaultarchmicroservice.controller.dto.ProductDto;
import com.defaultarchmicroservice.controller.form.ProductForm;
import com.defaultarchmicroservice.controller.form.ProductUpdateForm;
import com.defaultarchmicroservice.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.AllArgsConstructor;
import org.springdoc.api.annotations.ParameterObject;
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

    @Operation(summary = "Add new product")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",
                    description = "Created new product",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = ProductForm.class))
                    }
            ),
    })
    @PostMapping
    public ResponseEntity<ProductDto> post(@RequestBody ProductForm form) {
        ProductDto productDto = service.save(form);
        return ResponseEntity
                    .created(URI.create(String.format("/product/%s", productDto.getId())))
                    .body(productDto);
    }

    @Operation(summary = "Get a products by its id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                        description = "Found the product",
                        content = {
                                @Content(mediaType = "application/json",
                                schema = @Schema(implementation = ProductDto.class))
                        }
            ),
            @ApiResponse(responseCode = "204",
                        description = "Invalid id supplied",
                        content = @Content),
        })
    @GetMapping("/{id}")
    public ResponseEntity<ProductDto> getById(@PathVariable Long id) {
        Optional<ProductDto> product = service.get(id);
        return product
                .map(productDto -> ResponseEntity.ok().body(productDto))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NO_CONTENT).body(null));

    }

    @Operation(summary = "Get all products")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Found the product",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = ProductDto.class))
                    }
            ),
    })
    @GetMapping
    public PageList<List<ProductDto>> getAll(@PageableDefault(size = 10) @ParameterObject Pageable pagination){
        return  service.getAll(pagination);
    }

    @Operation(summary = "Update product")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Update product",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = ProductForm.class))
                    }
            ),
            @ApiResponse(responseCode = "404",
                    description = "Product not found",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = ProductForm.class))
                    }
            ),
    })
    @PutMapping("/{id}")
    public ResponseEntity<ProductDto> put(@RequestBody ProductUpdateForm form) {
        return  service.get(form.getId())
                .map(x -> ResponseEntity.ok().body(service.update(form)))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).body(null));
    }


    @Operation(summary = "Delete product")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Update product",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = ProductForm.class))
                    }
            ),
            @ApiResponse(responseCode = "404",
                    description = "Product not found",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = ProductForm.class))
                    }
            ),
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable Long id) {
        return  service.get(id)
                .map(x -> ResponseEntity.ok().body(service.delete(id)))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).body(null));
    }
}
