package com.natwest.restservice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.*;

@RestController
public class PrimesController {
    @GetMapping(path = "/api/primes/{n}")
    @ResponseBody
    public ResponseEntity<PrimeResponse> primes(@PathVariable int n){
        List<Integer> primesList = findPrimeNumbers(n);
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


    // generating the list of prime numbers from 2 to the given number
    // using the Sieve of Eratosthenes algorithm
    public static List<Integer> findPrimeNumbers(int n) {
        // initialize the array with "true", index denotes the numbers from 0 to n.
        boolean[] primes = new boolean[n + 1];
        Arrays.fill(primes, true);

        //loop from 2 to x until x*x becomes greater than n
        for (int i = 2; i * i < n; i++) {
            // process if the number is not already marked
            if (primes[i]) {
                // find the multiples and mark them as false
                for (int j = i * i; j <= n; j += i)
                    primes[j] = false;
            }
        }

        // populate the list of prime numbers from the array and return it
        List<Integer> primeNumbers = new ArrayList<>();
        for (int i = 2; i <= n; i++) {
            if (primes[i])
                primeNumbers.add(i);
        }
        return primeNumbers;
    }
}
