package com.api.userinfoservice.controller;

import org.springframework.web.bind.annotation.RestController;

import com.api.userinfoservice.service.UserServiceImpl;

import lombok.AllArgsConstructor;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.api.userinfoservice.exceptions.InvalidArgumentException;
import com.api.userinfoservice.models.*;

@AllArgsConstructor
@RestController
@RequestMapping("/user")
public class UserController {
    private final UserServiceImpl userService;

    @GetMapping("/{userId}")
    public ResponseEntity<User> getUser(@PathVariable String userId) {
        Long id;
        try {
            id = Long.valueOf(userId);
        }catch(Exception exception) {
            throw new InvalidArgumentException(userId);
        }
        return new ResponseEntity<>(userService.getUser(id), HttpStatus.OK);
    }
}
