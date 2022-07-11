package com.idfinance.watcher.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = CoinNotFound.class)
    public ResponseEntity<String> handleCoinNotFound(CoinNotFound exception){
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).
                body(exception.getMessage());
    }
}
