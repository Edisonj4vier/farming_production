package com.farming_production.farming_production.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor

public class SupplyProductDTO extends SupplyDTO {
    private ProductDTO product;
}
