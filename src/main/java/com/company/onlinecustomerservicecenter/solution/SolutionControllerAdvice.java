package com.company.onlinecustomerservicecenter.solution;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class SolutionControllerAdvice {
    @ExceptionHandler(value = {SolutionException.class})
    public ResponseEntity<String> solutionExceptionHandler(SolutionException e){
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }
}
