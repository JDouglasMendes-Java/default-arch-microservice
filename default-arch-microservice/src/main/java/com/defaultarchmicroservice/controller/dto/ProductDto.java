package com.defaultarchmicroservice.controller.dto;

import com.defaultarchmicroservice.model.Product;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode
public class ProductDto {
    private Long id;
    private String name;
    private Double value;
}
