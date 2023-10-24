package com.example.demoreactive.controllers;

import com.example.demoreactive.exception.InvalidHeaderException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(InvalidHeaderException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handleInvalidHeaderException(InvalidHeaderException ex) {
        return ex.getMessage();
    }
}

