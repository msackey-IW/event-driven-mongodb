package com.api.eventdrivenuserservice.web;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.eventdrivenuserservice.eda.solace.SolaceAdapter;
import com.api.eventdrivenuserservice.model.User;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/user")
@AllArgsConstructor
public class UserController {
    SolaceAdapter solaceAdapter;

    @PostMapping("/add")
    public ResponseEntity<User> addUser(@Valid User user) {
        try {
            solaceAdapter.publishMessage("Topic/User/Add", user.toString());
            return ResponseEntity.ok(user);
        } catch(Exception e) {
            // Log the exception or handle it appropriately
            return ResponseEntity.status(HttpStatus.OK).build();
        }
    }
    
}
