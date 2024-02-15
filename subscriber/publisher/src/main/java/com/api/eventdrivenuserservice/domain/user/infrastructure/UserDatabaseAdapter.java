package com.api.eventdrivenuserservice.domain.user.infrastructure;


import org.springframework.stereotype.Component;

import com.api.eventdrivenuserservice.domain.user.core.model.User;
import com.api.eventdrivenuserservice.domain.user.core.ports.outgoing.UserDatabase;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class UserDatabaseAdapter implements UserDatabase{
    private final UserRepository userRepository;

    @Override
    public User save(User user) {
       return (userRepository.save(user));
    }


    
}
