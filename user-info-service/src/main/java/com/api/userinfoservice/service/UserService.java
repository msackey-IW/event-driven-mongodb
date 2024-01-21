package com.api.userinfoservice.service;

import com.api.userinfoservice.models.User;

public interface UserService {
    User getUser(Long id);
    User addUser(User u);
}
