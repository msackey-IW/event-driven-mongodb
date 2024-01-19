package com.api.microservice.exceptions;

public class InvalidArgumentException extends RuntimeException {
    public InvalidArgumentException(String id) {
        super("'" + id + "'' is not a valid integer.");
    }
    
}
