package com.farming_production.farming_production.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.farming_production.farming_production.dto.NewSupplyDTO;
import com.farming_production.farming_production.dto.SupplyDTO;
import com.farming_production.farming_production.dto.SupplyProductDTO;
import com.farming_production.farming_production.exceptions.NoContentException;
import com.farming_production.farming_production.exceptions.ResourceNotFoundException;
import com.farming_production.farming_production.models.Product;
import com.farming_production.farming_production.models.Supply;
import com.farming_production.farming_production.repositories.ProductRepository;
import com.farming_production.farming_production.repositories.SupplyRepository;
import com.farming_production.farming_production.services.SupplyService;


@Service
public class SupplyServiceImpl implements SupplyService {
    final ModelMapper modelMapper;
    final SupplyRepository supplyRepository;
    final ProductRepository productRepository;

    public SupplyServiceImpl(SupplyRepository repository, ModelMapper mapper,
            ProductRepository productRepository) {
        this.supplyRepository = repository;
        this.modelMapper = mapper;
        this.productRepository = productRepository;
    }

    @Override
    @Transactional
    public SupplyDTO create(Long idProduct, NewSupplyDTO supplyDTO) {
        Product product = productRepository.findById(idProduct)
                .orElseThrow(() -> new ResourceNotFoundException("Product no found"));

        Supply supply = modelMapper.map(supplyDTO, Supply.class);
        supply.setProduct(product);
        supplyRepository.save(supply);
        return modelMapper.map(supply, SupplyDTO.class);
    }

    @Override
    @Transactional(readOnly = true)
    public SupplyProductDTO retrieve(Long idProduct, Long id) {
        Product product = productRepository.findById(idProduct)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found"));
        Supply supply = supplyRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Supply not found"));
        supply.setProduct(product);
        return modelMapper.map(supply, SupplyProductDTO.class);
    }

    @Override
    @Transactional
    public SupplyProductDTO update(SupplyDTO supplyDTO, Long idProduct, Long id) {
        Product product = productRepository.findById(idProduct)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found"));
        Supply supply = supplyRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Supply not found"));
        supply = modelMapper.map(supplyDTO, Supply.class);
        supply.setProduct(product);
        supplyRepository.save(supply);
        return modelMapper.map(supply, SupplyProductDTO.class);
    }

    @Override
    @Transactional
    public void delete(Long idProduct, Long id) {
        Product product = productRepository.findById(idProduct)
                .orElseThrow(() -> new ResourceNotFoundException("Exam not found"));
        Supply supply = supplyRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Question not found"));
        supply.setProduct(product);
        supplyRepository.deleteById(supply.getId());
    }

    @Override
    @Transactional(readOnly = true)
    public List<SupplyDTO> list(Long idProduct) {
        Product product = productRepository.findById(idProduct)
                .orElseThrow(() -> new ResourceNotFoundException("Product no found"));

        List<Supply> maintenances = supplyRepository.findByProduct(product);
        if (maintenances.isEmpty())
            throw new NoContentException("Supply is empty");
        return maintenances.stream().map(supply -> modelMapper.map(supply, SupplyDTO.class))
                .collect(Collectors.toList());
    }

}
