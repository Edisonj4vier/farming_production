package com.farming_production.farming_production.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ProductListDTO {
    
    private Long id;
    private String name;
    private String description;
    private String category;
    private String amount;
}
