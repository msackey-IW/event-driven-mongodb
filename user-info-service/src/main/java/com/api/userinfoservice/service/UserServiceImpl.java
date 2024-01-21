package com.api.UserInfoService.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.api.UserInfoService.exceptions.UserNotFoundException;
import com.api.UserInfoService.models.User;
import com.api.UserInfoService.repository.UserRepository;

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
