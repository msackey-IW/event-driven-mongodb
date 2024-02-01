package com.api.eventdrivenuserservice.service;


import java.util.Optional;

import org.springframework.stereotype.Service;

import com.api.eventdrivenuserservice.exception.UserNotFoundException;
import com.api.eventdrivenuserservice.model.User;
import com.api.eventdrivenuserservice.repository.UserRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {
    UserRepository userRepo;

    @Override
    public User getUser(Long id) {
        return unWrapUser(userRepo.getUserById(id), id);
    }

    public static User unWrapUser(Optional<User> user, Long id) {
        User u = user.orElse(null);
        if (u == null) {
            throw new UserNotFoundException(id);
        }
        return u;
    }

    @Override
    public User addUser(User u) {
        return userRepo.save(u);
    }
}

