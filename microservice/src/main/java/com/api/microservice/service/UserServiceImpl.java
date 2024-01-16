package com.api.microservice.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.api.microservice.exceptions.UserNotFoundException;
import com.api.microservice.models.User;
import com.api.microservice.repository.UserRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {
    UserRepository userRepo;

    @Override
    public User getUser(int id) {
        return unWrapUser(userRepo.getUserById(id), id);
    }

    public static User unWrapUser(Optional<User> user, int id) {
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
