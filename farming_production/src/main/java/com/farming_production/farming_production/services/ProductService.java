package com.farming_production.farming_production.services;

import java.util.List;

import com.farming_production.farming_production.dto.NewProductDTO;
import com.farming_production.farming_production.dto.ProductDTO;

public interface ProductService {
    
    public ProductDTO create(NewProductDTO productDTO);
    public ProductDTO retrieve(Long id);
    public ProductDTO update(ProductDTO productDTO, Long id);
    public void remove(Long id);

    public List<ProductDTO> list(int page, int size , String sort);
    long count();
}
