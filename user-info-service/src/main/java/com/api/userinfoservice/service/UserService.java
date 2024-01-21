package com.api.UserInfoService.service;

import com.api.UserInfoService.models.User;

public interface UserService {
    User getUser(Long id);
    User addUser(User u);
}
