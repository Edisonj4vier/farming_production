package com.farming_production.farming_production.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.farming_production.farming_production.models.User;

@Repository
public interface UserRepository extends JpaRepository<User,Long>{
    public User findByName(String name);
}
