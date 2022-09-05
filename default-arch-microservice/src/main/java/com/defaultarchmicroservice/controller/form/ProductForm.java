package com.defaultarchmicroservice.controller.form;


import com.defaultarchmicroservice.model.Product;
import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductForm {
    @NotNull
    @NotEmpty
    @Length(min = 5)
    @Length(max = 100)
    private  String name;
    @NotNull
    private double value;
}
