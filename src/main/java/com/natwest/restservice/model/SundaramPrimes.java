package com.natwest.restservice.model;

import org.springframework.cache.annotation.Cacheable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SundaramPrimes extends Primes{
    // generating the list of prime numbers from 2 to the given number
    // using the Sieve of Sundaram algorithm
    @Override
    @Cacheable("primes")
    public List<Integer> getPrimes(int n) {
        if(n < 1){
            n = 1;
        }

        // In general Sieve of Sundaram, produces
        // primes smaller than (2*x + 2) for a number
        // given number x. Since we want primes
        // smaller than n, we reduce n to half
        int nNew = (n - 1) / 2;

        // This array is used to separate numbers of the
        // form i+j+2ij from others where 1 <= i <= j
        boolean[] primes = new boolean[nNew + 1];

        // Initialize all elements as not marked
        Arrays.fill(primes, false);

        // Main logic of Sundaram. Mark all numbers of the
        // form i + j + 2ij as true where 1 <= i <= j
        for (int i = 1; i <= nNew; i++) {
            for (int j = i; (i + j + 2 * i * j) <= nNew; j++) {
                primes[i + j + 2 * i * j] = true;
            }
        }

        List<Integer> primeNumbers = new ArrayList<>();
        if(n > 1){
            primeNumbers.add(2);
        }
        for(int i = 1; i <= nNew; i++) {
            if (!primes[i]) {
                primeNumbers.add(2 * i + 1);
            }
        }
        return primeNumbers;
    }
}
