package com.natwest.restservice;

import java.util.Arrays;

import com.fasterxml.jackson.annotation.JsonCreator;

public class PrimeResponse {
    private final int[] numbers;

    @JsonCreator
    public PrimeResponse(int[] numbers){
        this.numbers = numbers;
    }

    public int[] getNumbers(){
        return numbers;
    }

    @Override
    public boolean equals(Object o){
        // If the object is compared with itself then return true
        if(o == this){
            return true;
        }

        // Check if o is an instance of PrimeResponse or not
        if(!(o instanceof PrimeResponse)){
            return false;
        }

        //typecast o to PrimeResponse so we can compare arrays
        PrimeResponse p = (PrimeResponse) o;

        //Compare PrimeResponse arrays and return accordingly
        return Arrays.equals(this.numbers, p.numbers);
    }
}
