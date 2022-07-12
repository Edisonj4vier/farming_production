package com.farming_production.farming_production.services;

import java.util.List;

import com.farming_production.farming_production.dto.MaintenanceDTO;
import com.farming_production.farming_production.dto.NewMaintenanceDTO;

public interface MaintenanceService {
    public MaintenanceDTO create(Long idProduct, NewMaintenanceDTO maintenanceDTO);
    public MaintenanceDTO retrieve(Long idProduct, Long id); // Necesitaria solo 1p 
    public MaintenanceDTO update(MaintenanceDTO maintenceDTO, Long idProduct, Long idMaintenance);
    public void delete(Long idProduct, Long id);

    public List<MaintenanceDTO> list(Long idProduct);

}
