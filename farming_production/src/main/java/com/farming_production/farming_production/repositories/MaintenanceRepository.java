package com.farming_production.farming_production.repositories;

import com.farming_production.farming_production.models.Maintenance;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MaintenanceRepository extends JpaRepository<Maintenance,Long>{
    
}
