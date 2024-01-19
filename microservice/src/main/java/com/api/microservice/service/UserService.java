package com.api.microservice.service;

import com.api.microservice.models.User;

public interface UserService {
    User getUser(Long id);
    User addUser(User u);
}
