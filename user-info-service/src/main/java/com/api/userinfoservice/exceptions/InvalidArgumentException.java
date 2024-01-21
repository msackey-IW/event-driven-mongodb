package com.api.UserInfoService.exceptions;

public class InvalidArgumentException extends RuntimeException {
    public InvalidArgumentException(String id) {
        super("'" + id + "'' is not a valid integer.");
    }
    
}
