package com.natwest.restservice;

public class Primes {
    private final long count;
    private final long[] primes;

    public Primes(long count, long[] primes){
        this.count = count;
        this.primes = primes;
    }

    public long getCount(){
        return count;
    }

    public long[] getPrimes(){
        return primes;
    }
}
