package com.example.Product.models;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ProductWrapper {
    private Product product;
    private String message;

    public ProductWrapper(Product product, String message) {
        this.product = product;
        this.message = message;
    }
}
