package com.farming_production.farming_production.repositories;

import com.farming_production.farming_production.models.Producto;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductoRepository extends JpaRepository<Producto,Long> {
    
}
