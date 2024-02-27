package com.company.onlinecustomerservicecenter.operator;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class OperatorExceptionAdvice {
    @ExceptionHandler(value = {OperatorException.class})
    public ResponseEntity<String>operatorException(OperatorException e)
    {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }
}

