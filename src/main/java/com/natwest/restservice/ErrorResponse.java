package com.natwest.restservice;

import com.fasterxml.jackson.annotation.JsonCreator;

public class ErrorResponse {
    private final int statusCode;
    private final String message;

    @JsonCreator
    public ErrorResponse(int statusCode, String message){
        this.statusCode = statusCode;
        this.message = message;
    }

    public int getStatusCode(){
        return statusCode;
    }

    public String getMessage(){
        return message;
    }
}
