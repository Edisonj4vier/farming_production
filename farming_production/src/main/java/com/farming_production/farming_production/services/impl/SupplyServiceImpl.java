package com.farming_production.farming_production.services.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.farming_production.farming_production.dto.NewSupplyDTO;
import com.farming_production.farming_production.dto.SupplyDTO;
import com.farming_production.farming_production.exceptions.NoContentException;
import com.farming_production.farming_production.exceptions.ResourceNotFoundException;
import com.farming_production.farming_production.models.Maintenance;
import com.farming_production.farming_production.models.Product;
import com.farming_production.farming_production.models.Supply;
import com.farming_production.farming_production.repositories.MaintenanceRepository;
import com.farming_production.farming_production.repositories.ProductRepository;
import com.farming_production.farming_production.repositories.SupplyRepository;
import com.farming_production.farming_production.services.SupplyService;

@Service
public class SupplyServiceImpl implements SupplyService{
    final ModelMapper modelMapper;
    final SupplyRepository supplyRepository;
    final MaintenanceRepository maintenanceRepository ; 
    final ProductRepository productRepository;  

    public SupplyServiceImpl(SupplyRepository supplyRepository, ModelMapper mapper,
    MaintenanceRepository maintenanceRepository , ProductRepository productRepository){
        this.supplyRepository = supplyRepository;
        this.modelMapper = mapper;
        this.maintenanceRepository = maintenanceRepository ;
        this.productRepository = productRepository ; 
    }

    @Override
    @Transactional //
    public List<SupplyDTO> create(Long idProduct, Long idMaintenance, List<NewSupplyDTO> supplies){
        Product product = productRepository.findById(idProduct)
        .orElseThrow(()-> new ResourceNotFoundException("Product not found"));
        
        Maintenance maintenance = maintenanceRepository.findById(idMaintenance)
        .orElseThrow(()-> new ResourceNotFoundException("Maintenance not found"));
        
        maintenance.setProduct(product);

        List<SupplyDTO> result = new ArrayList<SupplyDTO>();
        supplies.forEach(op -> {
            Supply supply = modelMapper.map(op, Supply.class);
            supply.setMaintenance(maintenance);
            supplyRepository.save(supply);
            result.add(modelMapper.map(supply, SupplyDTO.class));
        });        
        return result;
    }


    @Override
    @Transactional(readOnly = true)
    public List<SupplyDTO> list(Long idProduct, Long idMaintenance){
        Product product = productRepository.findById(idProduct).orElseThrow(()-> new ResourceNotFoundException("Prouct not found"));
        Maintenance maintenance = maintenanceRepository.findById(idMaintenance).orElseThrow(()-> new ResourceNotFoundException("Maintenance not found"));
        maintenance.setProduct(product);
        
        if(maintenance.getSupplies().isEmpty()) throw new NoContentException("Maintenance is empty");
        return maintenance.getSupplies().stream().map(op -> modelMapper.map(op, SupplyDTO.class))
        .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void remove(Long idExam, Long idQuestion) {
        Product product = productRepository.findById(idExam).orElseThrow(()-> new ResourceNotFoundException("Product not found"));
        Maintenance maintenance = maintenanceRepository.findById(idQuestion).orElseThrow(()-> new ResourceNotFoundException("Maintenance not found"));
        maintenance.setProduct(product);
        if(maintenance.getSupplies().isEmpty()) throw new NoContentException("Supply is empty");
        maintenance.getSupplies().forEach(op -> {
            supplyRepository.delete(op);            
        });                      
    }

}
