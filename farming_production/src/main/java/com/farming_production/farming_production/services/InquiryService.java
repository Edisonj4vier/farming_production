package com.farming_production.farming_production.services;

import java.util.List;

import com.farming_production.farming_production.dto.InquiryDTO;
import com.farming_production.farming_production.dto.NewInquiryDTO;

public interface InquiryService {
    
    public InquiryDTO create(NewInquiryDTO inquiryDTO);
    public InquiryDTO retrieve(Long id);
    public InquiryDTO update(InquiryDTO inquiryDTO, Long id);
    public void delete(Long id);

    public List<InquiryDTO> list();
}
