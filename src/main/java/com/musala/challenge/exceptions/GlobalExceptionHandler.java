package com.musala.challenge.exceptions;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @Value(value = "Any field can be null")
    private String fieldNull;



    @ExceptionHandler(value = NullFieldException.class)
    public ResponseEntity nullFieldException(NullFieldException e){
        return new ResponseEntity(fieldNull, HttpStatus.BAD_REQUEST);
    }

}
