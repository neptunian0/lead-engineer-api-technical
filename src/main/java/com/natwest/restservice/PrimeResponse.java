package com.natwest.restservice;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonCreator;

public class PrimeResponse {
    private final List<Integer> primes;

    @JsonCreator
    public PrimeResponse(List<Integer> primes){
        this.primes = primes;
    }

    public List<Integer> getPrimes(){
        return primes;
    }
}
