package com.farming_production.farming_production.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import com.farming_production.farming_production.dto.NewProductDTO;
import com.farming_production.farming_production.dto.ProductDTO;
import com.farming_production.farming_production.exceptions.NoContentException;
import com.farming_production.farming_production.exceptions.ResourceNotFoundException;
import com.farming_production.farming_production.models.Product;
import com.farming_production.farming_production.repositories.ProductRepository;
import com.farming_production.farming_production.services.ProductService;

@Service
public class ProductServiceImpl implements ProductService {
    final ModelMapper modelMapper;
    final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository repository, ModelMapper mapper){
        this.productRepository = repository;
        this.modelMapper = mapper;
    }

    @Override
    @Transactional
    public ProductDTO create(NewProductDTO productDTO){
        Product product = modelMapper.map(productDTO, Product.class);
        productRepository.save(product);
        return modelMapper.map(product,ProductDTO.class);
    }
    
    @Override
    @Transactional(readOnly = true)
    public ProductDTO retrieve(Long id) {
        Product product = productRepository.findById(id)
            .orElseThrow(()-> new ResourceNotFoundException("Product not found"));
        return modelMapper.map(product, ProductDTO.class);
    }

    @Override
    @Transactional
    public ProductDTO update(ProductDTO productDTO, Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found"));
     
        product.setId(id);
        product = modelMapper.map(productDTO, Product.class);
        productRepository.save(product);

        return modelMapper.map(product, ProductDTO.class);
    }
    
    @Override
    @Transactional
    public void remove(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Product not found"));
        productRepository.deleteById(product.getId());
    }

    @Override
    @Transactional(readOnly = true)
    public List<ProductDTO> list(int page , int size , String sort) {

        Pageable pageable = sort == null || sort.isEmpty() ? 
        PageRequest.of(page, size) 
    :   PageRequest.of(page, size,  Sort.by(sort));

        Page<Product> products = productRepository.findAll(pageable);
        if (products.isEmpty()) throw new NoContentException("Product is empty");
        return products.stream().map(product -> modelMapper.map(product, ProductDTO.class))
        .collect(Collectors.toList());
    }
        
    @Override
    public long count() {        
        return productRepository.count();
    
    }
    
}
