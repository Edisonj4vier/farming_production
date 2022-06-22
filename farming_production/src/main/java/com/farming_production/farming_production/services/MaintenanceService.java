package com.farming_production.farming_production.services;

import java.util.List;

import com.farming_production.farming_production.dto.MaintenanceDTO;
import com.farming_production.farming_production.dto.NewMaintenanceDTO;

public interface MaintenanceService {
    
    public MaintenanceDTO create(NewMaintenanceDTO maintenanceDTO);
    public MaintenanceDTO retrieve(Long id);
    public MaintenanceDTO update(MaintenanceDTO maintenanceDTO, Long id);
    public void delete(Long id);

    public List<MaintenanceDTO> list();
    
}
