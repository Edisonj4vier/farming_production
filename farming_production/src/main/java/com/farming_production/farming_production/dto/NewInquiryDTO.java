package com.farming_production.farming_production.dto;

import java.util.Date;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class NewInquiryDTO {
    
    private String title;
    private String observation;
    private Date date;
}
