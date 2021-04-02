package com.natwest.restservice;

import com.fasterxml.jackson.annotation.JsonCreator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Entity
public class PrimeRequest {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Min(value = 2, message = "Input cannot be less than 2")
    private final int number;
    // private boolean xml;
    // private String algorithm;

    @JsonCreator
    public PrimeRequest(int number){
        this.number = number;
    }

    public int getNumber(){
        return number;
    }
}
