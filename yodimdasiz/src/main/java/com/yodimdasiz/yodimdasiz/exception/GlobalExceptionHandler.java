package com.yodimdasiz.yodimdasiz.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler
    public ResponseEntity<?> handler(com.yodimdasiz.yodimdasiz.exception.BadRequest badRequest){
        return ResponseEntity.badRequest().body(badRequest.getMessage());
    }
}
