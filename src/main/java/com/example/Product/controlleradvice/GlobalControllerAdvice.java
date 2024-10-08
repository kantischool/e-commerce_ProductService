package com.example.Product.controlleradvice;

import com.example.Product.controllers.ProductControllers;
import com.example.Product.dtos.ErrorResponseDto;
import com.example.Product.exception.InvalidProductException;
import com.fasterxml.jackson.databind.exc.InvalidTypeIdException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalControllerAdvice {

    // all the mentioned exception here will be caught here whenever it will be thrown from any part of application
    @ExceptionHandler(InvalidProductException.class)
    public ResponseEntity<ErrorResponseDto> handeInvalidProductException(){
        return new ResponseEntity<>(new ErrorResponseDto("invalid product id from global", null), HttpStatus.NOT_FOUND);
    }
}
