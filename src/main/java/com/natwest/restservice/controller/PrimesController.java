package com.natwest.restservice.controller;

import com.natwest.restservice.dto.PrimeResponse;
import com.natwest.restservice.exception.AlgorithmNotFoundException;
import com.natwest.restservice.model.AtkinPrimes;
import com.natwest.restservice.model.EratosthenesPrimes;
import com.natwest.restservice.model.Primes;
import com.natwest.restservice.model.SundaramPrimes;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.*;

@RestController
public class PrimesController {
    private static Map<String, Primes> algorithms = new HashMap<>();
    static {
        Primes eratosthenes = new EratosthenesPrimes();
        Primes atkin = new AtkinPrimes();
        Primes sundaram = new SundaramPrimes();

        algorithms.put(eratosthenes.getAlgorithmName(), eratosthenes);
        algorithms.put(atkin.getAlgorithmName(), atkin);
        algorithms.put(sundaram.getAlgorithmName(), sundaram);
    }

    @ApiOperation(value = "Get the list of prime numbers up to and including input {n}.",
            notes = "The Sieve algorithm can be specified using the query parameter \"algorithm\", by supplying the " +
                    "values \"eratosthenes\", \"atkin\", or \"sundaram\". The input value of \"n\" can be negative, " +
                    "but an empty list will be returned. If no algorithm is specified, will default to " +
                    "\"eratosthenes\". Invalid (e.g. misspelled) algorithm names will return a 404 Not Found response")
    @GetMapping(path = "/api/primes/{n}")
    @ResponseBody
    public ResponseEntity<PrimeResponse> primes(@PathVariable int n,
                                                @RequestParam(name="algorithm",
                                                        required=false,
                                                        defaultValue="eratosthenes") String algorithm){
        algorithm = algorithm.toLowerCase();
        if(!algorithms.containsKey(algorithm)) throw new AlgorithmNotFoundException();
        Primes primes = algorithms.get(algorithm);
        List<Integer> primesList = primes.getPrimes(n);
        PrimeResponse primeResponse = new PrimeResponse(primes.getAlgorithmName(), primesList);
        return ResponseEntity.ok()
                .body(primeResponse);
    }
}
