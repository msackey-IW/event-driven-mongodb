package com.api.eventdrivenuserservice.domain.user.core.ports;

import org.springframework.stereotype.Service;

import com.api.eventdrivenuserservice.domain.user.core.model.User;
import com.api.eventdrivenuserservice.domain.user.core.ports.incoming.AddNewUser;
import com.api.eventdrivenuserservice.domain.user.core.ports.outgoing.UserDatabase;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class UserFacade implements AddNewUser{
    private final UserDatabase userDatabase;

    @Override
    public User handle(User user) {
       return userDatabase.save(user);
    }
    


    
}
