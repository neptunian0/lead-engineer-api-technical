package com.natwest.restservice;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class EratosthenesPrimes implements Primes{
    // generating the list of prime numbers from 2 to the given number
    // using the Sieve of Eratosthenes algorithm
    @Override
    @Cacheable("primes")
    public List<Integer> getPrimes(int n){
        //protect against negative inputs; outputs an empty list
        if(n < 0){
            n = 0;
        }

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
