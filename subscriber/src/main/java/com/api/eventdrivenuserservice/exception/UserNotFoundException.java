package com.api.eventdrivenuserservice.exception;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(Long id) {
        super("The User with id '" + id + "' does not exist in our records");
    }

}
