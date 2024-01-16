package com.api.microservice.service;

import com.api.microservice.models.User;

public interface UserService {
    User getUser(int id);
    User addUser(User u);
}
