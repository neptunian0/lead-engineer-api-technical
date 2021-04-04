package com.natwest.restservice.controller;

import com.natwest.restservice.dto.PrimeResponse;
import com.natwest.restservice.exception.AlgorithmNotFoundException;
import com.natwest.restservice.model.AtkinPrimes;
import com.natwest.restservice.model.EratosthenesPrimes;
import com.natwest.restservice.model.Primes;
import com.natwest.restservice.model.SundaramPrimes;
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

        algorithms.put("eratosthenes", eratosthenes);
        algorithms.put("atkin", atkin);
        algorithms.put("sundaram", sundaram);
    }

    @GetMapping(path = "/api/primes/{n}")
    @ResponseBody
    public ResponseEntity<PrimeResponse> primes(@PathVariable int n,
                                                @RequestParam(name="algorithm",
                                                        required=false,
                                                        defaultValue="eratosthenes") String algorithm){
        if(!algorithms.containsKey(algorithm)) throw new AlgorithmNotFoundException();
        List<Integer> primesList = algorithms.get(algorithm).getPrimes(n);
        PrimeResponse primeResponse = new PrimeResponse(primesList);
        return ResponseEntity.ok()
                .body(primeResponse);
    }
}
