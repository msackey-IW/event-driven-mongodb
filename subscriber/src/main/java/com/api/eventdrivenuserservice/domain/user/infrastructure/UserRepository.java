package com.api.eventdrivenuserservice.domain.user.infrastructure;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.api.eventdrivenuserservice.domain.user.core.model.User;

@Repository
public interface UserRepository extends MongoRepository<User, String>{
    public long count();
    
}
