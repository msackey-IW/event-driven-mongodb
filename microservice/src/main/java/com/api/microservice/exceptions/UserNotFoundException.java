package com.api.microservice.exceptions;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(int id) {
        super("The User with id " + id + " does not exist in our records");
    }
    
}
