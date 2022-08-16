package com.farming_production.farming_production.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.farming_production.farming_production.dto.MaintenanceDTO;
import com.farming_production.farming_production.dto.MaintenanceProductDTO;
import com.farming_production.farming_production.dto.NewMaintenanceDTO;
import com.farming_production.farming_production.exceptions.NoContentException;
import com.farming_production.farming_production.exceptions.ResourceNotFoundException;
import com.farming_production.farming_production.models.Maintenance;
import com.farming_production.farming_production.models.Product;
import com.farming_production.farming_production.repositories.MaintenanceRepository;
import com.farming_production.farming_production.repositories.ProductRepository;
import com.farming_production.farming_production.services.MaintenanceService;

@Service
public class MaintenanceServiceImpl implements MaintenanceService {
    final ModelMapper modelMapper;
    final MaintenanceRepository maintenanceRepository;
    final ProductRepository productRepository;

    public MaintenanceServiceImpl(MaintenanceRepository repository, ModelMapper mapper,
            ProductRepository productRepository) {
        this.maintenanceRepository = repository;
        this.modelMapper = mapper;
        this.productRepository = productRepository;
    }

    @Override
    @Transactional
    public MaintenanceDTO create(Long idProduct, NewMaintenanceDTO maintenanceDTO) {
        Product product = productRepository.findById(idProduct)
                .orElseThrow(() -> new ResourceNotFoundException("Product no found"));

        Maintenance maintenance = modelMapper.map(maintenanceDTO, Maintenance.class);
        maintenance.setProduct(product);
        maintenanceRepository.save(maintenance);
        return modelMapper.map(maintenance, MaintenanceDTO.class);
    }

    @Override
    @Transactional(readOnly = true)
    public MaintenanceProductDTO retrieve(Long idProduct, Long id) {
        Product product = productRepository.findById(idProduct)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found"));
        Maintenance maintenance = maintenanceRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Maintenance not found"));
        maintenance.setProduct(product);
        return modelMapper.map(maintenance, MaintenanceProductDTO.class);
    }

    @Override
    @Transactional
    public MaintenanceProductDTO update(MaintenanceDTO maintenanceDTO, Long idProduct, Long id) {
        Product product = productRepository.findById(idProduct)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found"));
        Maintenance maintenance = maintenanceRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Maintenance not found"));
        maintenance = modelMapper.map(maintenanceDTO, Maintenance.class);
        maintenance.setProduct(product);
        maintenanceRepository.save(maintenance);
        return modelMapper.map(maintenance, MaintenanceProductDTO.class);
    }

    @Override
    @Transactional
    public void delete(Long idProduct, Long id) {
        Product product = productRepository.findById(idProduct)
                .orElseThrow(() -> new ResourceNotFoundException("Exam not found"));
        Maintenance maintenance = maintenanceRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Question not found"));
        maintenance.setProduct(product);
        maintenanceRepository.deleteById(maintenance.getId());
    }

    @Override
    @Transactional(readOnly = true)
    public List<MaintenanceDTO> list(Long idProduct) {
        Product product = productRepository.findById(idProduct)
                .orElseThrow(() -> new ResourceNotFoundException("Product no found"));

        List<Maintenance> maintenances = maintenanceRepository.findByProduct(product);
        if (maintenances.isEmpty())
            throw new NoContentException("Maintenance is empty");
        return maintenances.stream().map(maintenance -> modelMapper.map(maintenance, MaintenanceDTO.class))
                .collect(Collectors.toList());
    }

}
