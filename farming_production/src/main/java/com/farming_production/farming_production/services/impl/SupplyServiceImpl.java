package com.farming_production.farming_production.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.farming_production.farming_production.dto.NewSupplyDTO;
import com.farming_production.farming_production.dto.SupplyDTO;
import com.farming_production.farming_production.exceptions.ResourceNotFoundException;
import com.farming_production.farming_production.models.Supply;
import com.farming_production.farming_production.repositories.SupplyRepository;
import com.farming_production.farming_production.services.SupplyService;

@Service
public class SupplyServiceImpl implements SupplyService{
    final ModelMapper modelMapper;
    final SupplyRepository supplyRepository;

    @Autowired
    public SupplyServiceImpl(@Autowired SupplyRepository repository, ModelMapper mapper){
        this.supplyRepository = repository;
        this.modelMapper = mapper;
    }

    @Override
    @Transactional
    public SupplyDTO create(NewSupplyDTO supplyDTO){
        Supply supply = modelMapper.map(supplyDTO, Supply.class);
        supplyRepository.save(supply);
        return modelMapper.map(supply,SupplyDTO.class);
    }
    
    @Override
    @Transactional(readOnly = true)
    public SupplyDTO retrieve(Long id) {
        Supply supply = supplyRepository.findById(id)
            .orElseThrow(()-> new ResourceNotFoundException("Supply not found"));
        return modelMapper.map(supply, SupplyDTO.class);
    }

    @Override
    @Transactional
    public SupplyDTO update(SupplyDTO supplyDTO, Long id) {
        Supply supply = supplyRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Supply not found"));
        supply.setId(id);
        supply = modelMapper.map(supplyDTO, Supply.class);
        supplyRepository.save(supply);

        return modelMapper.map(supply, SupplyDTO.class);
    }
    
    @Override
    @Transactional
    public void delete(Long id) {
        Supply supply = supplyRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Supply not found"));
        supplyRepository.deleteById(supply.getId());
    }

    @Override
    @Transactional(readOnly = true)
    public List<SupplyDTO> list() {
        List<Supply> supplys = supplyRepository.findAll();
        return supplys.stream().map(supply -> modelMapper.map(supply, SupplyDTO.class)).collect(Collectors.toList());
    }
    
}
