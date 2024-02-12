package com.product.log.dto;

import lombok.Data;
import org.springframework.lang.NonNull;

@Data
public class DiscountOrTax {

    @NonNull
    private String productId;

    @NonNull
    private String discountOrTax;

    @NonNull
    private Double discountOrTaxPercentage;
}
