package com.farming_production.farming_production.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class NewMaintenanceDTO {
    
    private String name;
    private String description;
    private String date;
    private String state;
}
