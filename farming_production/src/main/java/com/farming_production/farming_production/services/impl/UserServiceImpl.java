package com.farming_production.farming_production.services.impl;

import org.springframework.stereotype.Service;
import com.farming_production.farming_production.models.User;
import com.farming_production.farming_production.repositories.UserRepository;
import com.farming_production.farming_production.services.UserService;

@Service
public class UserServiceImpl implements UserService {

    final UserRepository userRepository;

    public UserServiceImpl(UserRepository repository){
        this.userRepository = repository;
    }

    @Override
    public void create(User newUser){
        userRepository.save(newUser);
    }
    
    
}
