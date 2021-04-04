package com.natwest.restservice.model;

import org.springframework.cache.annotation.Cacheable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AtkinPrimes implements Primes{
    // generating the list of prime numbers from 2 to the given number
    // using the Sieve of Atkin algorithm
    @Override
    @Cacheable("primes")
    public List<Integer> getPrimes(int n){
        //protect against negative inputs; outputs an empty list
        if(n < 0){
            n = 0;
        }

        // Initialise the sieve array with false values
        boolean[] primes = new boolean[n + 1];
        Arrays.fill(primes, false);

        /*
         *  Mark primes[n] as true if one of the following is true:
         *  a) n = (4*x*x)+(y*y) has odd number of solutions, i.e., there exist
         *    odd number of distinct pairs (x, y) that satisfy the equation
         *    and n % 12 = 1 or n % 12 = 5.
         *  b) n = (3*x*x)+(y*y) has odd number of solutions and n % 12 = 7
         *  c) n = (3*x*x)-(y*y) has odd number of solutions, x > y and n % 12 = 11
         */
        for (int x = 1; x * x < n; x++) {
            for (int y = 1; y * y < n; y++) {

                // Main part of Sieve of Atkin
                int z = (4 * x * x) + (y * y);
                if (z <= n && (z % 12 == 1 || z % 12 == 5)) {
                    primes[z] ^= true;
                }

                z = (3 * x * x) + (y * y);
                if (z <= n && z % 12 == 7) {
                    primes[z] ^= true;
                }

                z = (3 * x * x) - (y * y);
                if (x > y && z <= n && z % 12 == 11) {
                    primes[z] ^= true;
                }
            }
        }

        // Mark all multiples of squares as non-prime
        for (int r = 5; r * r < n; r++) {
            if (primes[r]) {
                for (int i = r * r; i < n; i += r * r) {
                    primes[i] = false;
                }
            }
        }

        List<Integer> primeNumbers = new ArrayList<>();
        // 2 and 3 are known to be prime
        if(n > 1){
            primeNumbers.add(2);
        }
        if(n > 2){
            primeNumbers.add(3);
        }

        // Add primes from primes[] to primes list
        for (int i = 5; i <= n; i++) {
            if (primes[i]) {
                primeNumbers.add(i);
            }
        }
        return primeNumbers;
    }
}
