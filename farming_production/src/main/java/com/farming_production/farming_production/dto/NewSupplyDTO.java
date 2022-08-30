package com.farming_production.farming_production.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class NewSupplyDTO {

    private String name;
    private String description;
    private short amount;
}
