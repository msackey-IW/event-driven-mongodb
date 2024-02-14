package com.api.eventdrivenuserservice.web;

import org.apache.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
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
        solaceAdapter.publishMessage("Topic/User/Add", user.toString());
        return new ResponseEntity<>(user, HttpStatus.SC_OK);
     

    }
}
