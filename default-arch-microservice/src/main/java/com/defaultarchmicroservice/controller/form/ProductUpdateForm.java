package com.defaultarchmicroservice.controller.form;

import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductUpdateForm {
    @NotNull
    @NotEmpty
    @Length(min = 5)
    @Length(max = 100)
    private  String name;
    @NotNull
    private double value;
    @Positive
    private Long id;
}
