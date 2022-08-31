package com.farming_production.farming_production.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.farming_production.farming_production.models.Role;
import com.farming_production.farming_production.models.User;
import com.farming_production.farming_production.repositories.UserRepository;
import com.farming_production.farming_production.services.UserService;


@Service
public class UserServiceImpl implements UserService  , UserDetailsService{

    final UserRepository userRepository;

    public UserServiceImpl(UserRepository repository){
        this.userRepository = repository;        
    }

    @Override
    public void create(User newUser) {
        Role role = new Role();
        role.setRole("ROLE_WORKER");
        newUser.getRoles().add(role);
        userRepository.save(newUser);                
    }

    private Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    
    @Override
	@Transactional(readOnly=true)
	public UserDetails loadUserByUsername(String name ) throws UsernameNotFoundException {

        User user = userRepository.findByName(name);
        
        if(user == null) {
        	logger.error("User "+ name + " not found");
        	throw new UsernameNotFoundException("Error: User" + name + " not found");
        }

        if (user.getPassword().equals("jgvajvajv")){
            logger.error("User "+ name + " not found");
        	throw new UsernameNotFoundException("Error: Wrong Password");
        }

        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
        
        for(Role role: user.getRoles()) {
        	logger.info("Role: ".concat(role.getRole()));
        	authorities.add(new SimpleGrantedAuthority(role.getRole()));
        }
        
        if(authorities.isEmpty()) {
        	logger.error("User " + user.getName() + " has no roles");
        	throw new UsernameNotFoundException("Error: User " + user.getName() +" has no roles");
        }
        
		return new org.springframework.security.core.userdetails.User(user.getName(), 
            user.getPassword(), 
            user.getEnabled(), 
            true, 
            true, 
            true, authorities);
	}

    
}
