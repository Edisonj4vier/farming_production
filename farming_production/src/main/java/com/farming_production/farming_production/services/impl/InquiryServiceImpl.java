package com.farming_production.farming_production.services.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.farming_production.farming_production.dto.InquiryDTO;
import com.farming_production.farming_production.dto.NewInquiryDTO;
import com.farming_production.farming_production.models.Inquiry;
import com.farming_production.farming_production.repositories.InquiryRepository;
import com.farming_production.farming_production.services.InquiryService;

@Service
public class InquiryServiceImpl implements InquiryService{
    final ModelMapper modelMapper;
    final InquiryRepository inquiryRepository;

    @Autowired
    public InquiryServiceImpl(@Autowired InquiryRepository repository, ModelMapper mapper){
        this.inquiryRepository = repository;
        this.modelMapper = mapper;
    }

    @Override
    @Transactional
    public InquiryDTO create(NewInquiryDTO inquiryDTO){
        Inquiry inquiry = modelMapper.map(inquiryDTO, Inquiry.class);
        inquiryRepository.save(inquiry);
        return modelMapper.map(inquiry,InquiryDTO.class);
    }
    
    
}
