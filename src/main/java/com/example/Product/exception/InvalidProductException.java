package com.example.Product.exception;

import com.fasterxml.jackson.databind.exc.InvalidTypeIdException;

public class InvalidProductException extends Exception {
    public InvalidProductException(String message){
        super(message);
    }
}
