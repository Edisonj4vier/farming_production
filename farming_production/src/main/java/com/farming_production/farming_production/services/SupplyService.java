package com.farming_production.farming_production.services;

import java.util.List;

import com.farming_production.farming_production.dto.NewSupplyDTO;
import com.farming_production.farming_production.dto.SupplyDTO;

public interface SupplyService {
    public SupplyDTO create(NewSupplyDTO supplyDTO);
    public SupplyDTO retrieve(Long id);
    public SupplyDTO update(SupplyDTO supplyDTO, Long id);
    public void delete(Long id);

    public List<SupplyDTO> list();
    
}
