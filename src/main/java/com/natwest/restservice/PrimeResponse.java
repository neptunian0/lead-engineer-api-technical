package com.natwest.restservice;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonCreator;

public class PrimeResponse {
    private final List<Integer> numbers;

    @JsonCreator
    public PrimeResponse(List<Integer> numbers){
        this.numbers = numbers;
    }

    public List<Integer> getNumbers(){
        return numbers;
    }
}
