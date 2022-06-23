package com.farming_production.farming_production.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.farming_production.farming_production.dto.NewUserDTO;
import com.farming_production.farming_production.dto.UserDTO;
import com.farming_production.farming_production.exceptions.ResourceNotFoundException;
import com.farming_production.farming_production.models.User;
import com.farming_production.farming_production.repositories.UserRepository;
import com.farming_production.farming_production.services.UserService;

@Service
public class UserServiceImpl implements UserService {
    final ModelMapper modelMapper;
    final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(@Autowired UserRepository repository, ModelMapper mapper){
        this.userRepository = repository;
        this.modelMapper = mapper;
    }

    @Override
    @Transactional
    public UserDTO create(NewUserDTO userDTO){
        User user = modelMapper.map(userDTO, User.class);
        userRepository.save(user);
        return modelMapper.map(user,UserDTO.class);
    }
    
    @Override
    @Transactional(readOnly = true)
    public UserDTO retrieve(Long id) {
        User user = userRepository.findById(id)
            .orElseThrow(()-> new ResourceNotFoundException("User not found"));
        return modelMapper.map(user, UserDTO.class);
    }

    @Override
    @Transactional
    public UserDTO update(UserDTO userDTO, Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User not found"));
        user.setId(id);
        user = modelMapper.map(userDTO, User.class);
        userRepository.save(user);

        return modelMapper.map(user, UserDTO.class);
    }
    
    @Override
    @Transactional
    public void delete(Long id) {
        User user = userRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("User not found"));
        userRepository.deleteById(user.getId());
    }

    @Override
    @Transactional(readOnly = true)
    public List<UserDTO> list() {
        List<User> users = userRepository.findAll();
        return users.stream().map(user -> modelMapper.map(user, UserDTO.class)).collect(Collectors.toList());
    }
    
}
