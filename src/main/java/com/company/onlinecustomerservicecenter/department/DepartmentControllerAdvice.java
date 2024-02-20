package com.company.onlinecustomerservicecenter.department;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class DepartmentControllerAdvice {
    @ExceptionHandler(value = DepartmentException.class)
    public ResponseEntity<String> handleDepartmentException(DepartmentException e){
        return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
    }
}
