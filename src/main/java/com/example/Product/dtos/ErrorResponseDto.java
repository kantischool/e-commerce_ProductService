package com.example.Product.dtos;

import com.example.Product.models.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ErrorResponseDto {

    private String message;
    private Product product;

}
