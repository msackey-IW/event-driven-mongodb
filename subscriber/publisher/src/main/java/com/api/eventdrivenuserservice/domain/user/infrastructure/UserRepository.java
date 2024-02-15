package com.api.eventdrivenuserservice.domain.user.infrastructure;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.api.eventdrivenuserservice.domain.user.core.model.User;

@Repository
public interface UserRepository extends CrudRepository<User, Long>{
    
}
