package com.farming_production.farming_production.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.farming_production.farming_production.dto.InquiryDTO;
import com.farming_production.farming_production.dto.NewInquiryDTO;
import com.farming_production.farming_production.exceptions.ResourceNotFoundException;
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
    
    @Override
    @Transactional(readOnly = true)
    public InquiryDTO retrieve(Long id) {
        Inquiry inquiry = inquiryRepository.findById(id)
            .orElseThrow(()-> new ResourceNotFoundException("Inquiry not found"));
        return modelMapper.map(inquiry, InquiryDTO.class);
    }

    @Override
    @Transactional
    public InquiryDTO update(InquiryDTO inquiryDTO, Long id) {
        Inquiry inquiry = inquiryRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Inquiry not found"));
        inquiry.setId(id);
        inquiry = modelMapper.map(inquiryDTO, Inquiry.class);
        inquiryRepository.save(inquiry);

        return modelMapper.map(inquiry, InquiryDTO.class);
    }
    
    @Override
    @Transactional
    public void delete(Long id) {
        Inquiry inquiry = inquiryRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Inquiry not found"));
        inquiryRepository.deleteById(inquiry.getId());
    }

    @Override
    @Transactional(readOnly = true)
    public List<InquiryDTO> list() {
        List<Inquiry> inquirys = inquiryRepository.findAll();
        return inquirys.stream().map(inquiry -> modelMapper.map(inquiry, InquiryDTO.class)).collect(Collectors.toList());
    }
}
