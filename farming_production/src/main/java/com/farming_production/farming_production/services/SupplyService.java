package com.farming_production.farming_production.services;

import java.util.List;

import com.farming_production.farming_production.dto.NewSupplyDTO;
import com.farming_production.farming_production.dto.SupplyDTO;

public interface SupplyService {
    
    public List<SupplyDTO> create(Long idProduct, Long idMaintenance, List<NewSupplyDTO> supplies);
    public List<SupplyDTO> list(Long idProduct, Long idMaintenance);
    public void remove(Long idExam, Long idMaintenance);
}
