package com.natwest.restservice.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class Primes {
    private String algorithmName;

    public Primes(String algorithmName){
        this.algorithmName = algorithmName;
    }

    public String getAlgorithmName(){
        return algorithmName;
    }

    public abstract List<Integer> getPrimes(int n);
}
