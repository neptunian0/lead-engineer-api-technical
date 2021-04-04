package com.natwest.restservice.controller;

import com.natwest.restservice.dto.PrimeResponse;
import com.natwest.restservice.model.EratosthenesPrimes;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
public class PrimesController {
    @GetMapping(path = "/api/primes/{n}")
    @ResponseBody
    public ResponseEntity<PrimeResponse> primes(@PathVariable int n){
        EratosthenesPrimes primes = new EratosthenesPrimes();
        List<Integer> primesList = primes.getPrimes(n);
        PrimeResponse primeResponse = new PrimeResponse(primesList);
        return ResponseEntity.ok()
                .body(primeResponse);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }
}
