package com.farming_production.farming_production.repositories;

import com.farming_production.farming_production.models.Maintenance;
import com.farming_production.farming_production.models.Product;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MaintenanceRepository extends JpaRepository<Maintenance,Long>{
    public List<Maintenance> findByProduct(Product product);

}
