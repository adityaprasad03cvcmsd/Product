package com.product.log.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.NonNull;

@Data
@NoArgsConstructor
public class ProductRequest {

    @NonNull
    private String name;

    @NonNull
    private String description;

    @NonNull
    private Integer quantityAvailable;

    @NonNull
    private Double price;
}
