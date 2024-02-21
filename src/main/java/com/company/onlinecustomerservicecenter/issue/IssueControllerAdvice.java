package com.company.onlinecustomerservicecenter.issue;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class IssueControllerAdvice {
    @ExceptionHandler(value = {IssueException.class})
    public ResponseEntity<String> handleIssueException(IssueException e){
        return ResponseEntity.badRequest().body(e.getMessage());
    }
}
