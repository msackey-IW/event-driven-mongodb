package com.api.eventdrivenuserservice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import java.util.Collections;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.api.eventdrivenuserservice.exception.ExceptionResponse;
import com.api.eventdrivenuserservice.exception.InvalidArgumentException;
import com.api.eventdrivenuserservice.exception.UserNotFoundException;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler{

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<Object> handleUserNotFoundException(UserNotFoundException ex) {
        ExceptionResponse response = new ExceptionResponse(Collections.singletonList(ex.getLocalizedMessage()));
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(InvalidArgumentException.class)
    public ResponseEntity<Object> handleInvalidArgException(InvalidArgumentException ex) {
        ExceptionResponse response = new ExceptionResponse(Collections.singletonList(ex.getLocalizedMessage()));
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

}
