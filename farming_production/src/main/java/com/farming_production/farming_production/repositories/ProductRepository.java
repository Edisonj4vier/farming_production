package com.farming_production.farming_production.repositories;

import com.farming_production.farming_production.models.Product;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product,Long> {
    
}
