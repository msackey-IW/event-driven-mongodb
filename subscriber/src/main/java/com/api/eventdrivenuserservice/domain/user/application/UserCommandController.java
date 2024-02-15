package com.api.eventdrivenuserservice.domain.user.application;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.eventdrivenuserservice.domain.user.core.model.User;
import com.api.eventdrivenuserservice.domain.user.core.ports.outgoing.UserEventPublisher;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;


@RestController
@RequestMapping("/users")
@AllArgsConstructor
public class UserCommandController {
    UserEventPublisher userEventPublisher;
    @PostMapping("/add")
    public ResponseEntity<String> addNewUser(@RequestBody @Valid User user) throws Exception{
        userEventPublisher.publish(user);
        return new ResponseEntity<>("New user was successfully published.", HttpStatus.OK);
    }

    
}
