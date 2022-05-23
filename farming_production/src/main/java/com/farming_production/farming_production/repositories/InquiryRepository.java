package com.farming_production.farming_production.repositories;

import com.farming_production.farming_production.models.Inquiry;

import org.springframework.data.jpa.repository.JpaRepository;

public interface InquiryRepository extends JpaRepository<Inquiry,Long>{
    
}
