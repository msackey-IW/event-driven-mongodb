package com.api.eventdrivenuserservice.domain.user.core.ports.incoming;

import org.springframework.stereotype.Component;

import com.api.eventdrivenuserservice.domain.user.core.model.User;

@Component
public interface AddNewUser {
   User handle(User user);
}
