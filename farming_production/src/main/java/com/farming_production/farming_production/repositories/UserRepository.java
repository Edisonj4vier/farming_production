package com.farming_production.farming_production.repositories;


import com.farming_production.farming_production.models.User;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long>{
    
}
