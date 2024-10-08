package com.example.Product.dtos;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ProductResponse {
    private Long id;
    private String title;
    private int price;
    private String category;
    private String description;
    private String image;
}
