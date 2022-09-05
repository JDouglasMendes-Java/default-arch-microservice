package com.defaultarchmicroservice.controller.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PageList<T> {
    private int amountItems;
    private int totalPages;
    private  T items;
}
