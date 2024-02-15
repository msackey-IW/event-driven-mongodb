package com.api.eventdrivenuserservice.domain.user.core.ports.outgoing;

import org.springframework.stereotype.Component;
import com.api.eventdrivenuserservice.domain.user.core.model.User;

@Component
public interface UserDatabase {
    User save(User user);
    
    
}
