package com.natwest.restservice.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModelProperty;

public class PrimeResponse {
    @ApiModelProperty(value = "Available options include \"eratosthenes\", \"atkin\", and \"sundaram\"")
    private final String algorithm;
    private final List<Integer> primes;

    @JsonCreator
    public PrimeResponse(String algorithm, List<Integer> primes){
        this.algorithm = algorithm;
        this.primes = primes;
    }

    public String getAlgorithm() { return algorithm; }

    public List<Integer> getPrimes(){
        return primes;
    }
}
