package com.farming_production.farming_production.dto;

import java.util.Date;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class NewMaintenanceDTO {
    
    private String name;
    private String description;
    private Date date;
    private String amount;
    private String state;
}
