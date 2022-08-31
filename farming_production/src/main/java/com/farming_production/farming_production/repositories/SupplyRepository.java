package com.farming_production.farming_production.repositories;


import com.farming_production.farming_production.models.Product;
import com.farming_production.farming_production.models.Supply;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface SupplyRepository extends JpaRepository<Supply,Long>{
    public List<Supply> findByProduct(Product product);

}
