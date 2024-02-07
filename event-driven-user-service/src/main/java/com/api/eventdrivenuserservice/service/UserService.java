package com.api.eventdrivenuserservice.service;

import com.api.eventdrivenuserservice.model.User;

public interface UserService {
    User getUser(Long id);
    User addUser(User u);
}
