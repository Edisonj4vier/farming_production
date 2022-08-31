package com.farming_production.farming_production.services;

import java.util.List;

import com.farming_production.farming_production.dto.NewSupplyDTO;
import com.farming_production.farming_production.dto.SupplyDTO;
import com.farming_production.farming_production.dto.SupplyProductDTO;

public interface SupplyService {
    public SupplyDTO create(Long idProduct, NewSupplyDTO maintenanceDTO);
    public SupplyProductDTO retrieve(Long idProduct, Long id);
    public SupplyProductDTO update(SupplyDTO supplyDTO, Long idProduct, Long idSupply);
    public void delete(Long idProduct, Long id);
    public List<SupplyDTO> list(Long idProduct);
}
