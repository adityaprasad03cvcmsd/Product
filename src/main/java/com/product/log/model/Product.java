package com.product.log.model;

import com.product.log.dto.ProductRequest;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
public class Product {

    private String ProductId;

    private String Name;

    private String Description;

    private Double Price;

    private Integer QuantityAvailable;

    public Product(ProductRequest productRequest) {

        this.ProductId = UUID.randomUUID().toString();
        this.Name = productRequest.getName();
        this.Description = productRequest.getDescription();
        this.Price = productRequest.getPrice();
        this.QuantityAvailable = productRequest.getQuantityAvailable();

    }
}
