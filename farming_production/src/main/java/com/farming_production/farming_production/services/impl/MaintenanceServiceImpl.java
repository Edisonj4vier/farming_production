package com.farming_production.farming_production.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.farming_production.farming_production.dto.MaintenanceDTO;
import com.farming_production.farming_production.dto.NewMaintenanceDTO;
import com.farming_production.farming_production.exceptions.ResourceNotFoundException;
import com.farming_production.farming_production.models.Maintenance;
import com.farming_production.farming_production.repositories.MaintenanceRepository;
import com.farming_production.farming_production.services.MaintenanceService;

@Service
public class MaintenanceServiceImpl implements MaintenanceService{
    final ModelMapper modelMapper;
    final MaintenanceRepository maintenanceRepository;

    @Autowired
    public MaintenanceServiceImpl(@Autowired MaintenanceRepository repository, ModelMapper mapper){
        this.maintenanceRepository = repository;
        this.modelMapper = mapper;
    }

    @Override
    @Transactional
    public MaintenanceDTO create(NewMaintenanceDTO maintenanceDTO){
        Maintenance maintenance = modelMapper.map(maintenanceDTO, Maintenance.class);
        maintenanceRepository.save(maintenance);
        return modelMapper.map(maintenance,MaintenanceDTO.class);
    }
    
    @Override
    @Transactional(readOnly = true)
    public MaintenanceDTO retrieve(Long id) {
        Maintenance maintenance = maintenanceRepository.findById(id)
            .orElseThrow(()-> new ResourceNotFoundException("Maintenance not found"));
        return modelMapper.map(maintenance, MaintenanceDTO.class);
    }

    @Override
    @Transactional
    public MaintenanceDTO update(MaintenanceDTO maintenanceDTO, Long id) {
        Maintenance maintenance = maintenanceRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Maintenance not found"));
        maintenance.setId(id);
        maintenance = modelMapper.map(maintenanceDTO, Maintenance.class);
        maintenanceRepository.save(maintenance);

        return modelMapper.map(maintenance, MaintenanceDTO.class);
    }
    
    @Override
    @Transactional
    public void delete(Long id) {
        Maintenance maintenance = maintenanceRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Maintenance not found"));
        maintenanceRepository.deleteById(maintenance.getId());
    }

    @Override
    @Transactional(readOnly = true)
    public List<MaintenanceDTO> list() {
        List<Maintenance> maintenances =maintenanceRepository.findAll();
        return maintenances.stream().map(maintenance -> modelMapper.map(maintenance,MaintenanceDTO.class)).collect(Collectors.toList());
    }

    
}
