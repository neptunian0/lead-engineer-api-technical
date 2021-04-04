package com.natwest.restservice.controller;

import com.natwest.restservice.exception.AlgorithmNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class AlgorithmExceptionController {
    @ExceptionHandler(value = AlgorithmNotFoundException.class)
    public ResponseEntity<Object> exception(AlgorithmNotFoundException exception) {
        return new ResponseEntity<>("Algorithm not found", HttpStatus.NOT_FOUND);
    }
}
