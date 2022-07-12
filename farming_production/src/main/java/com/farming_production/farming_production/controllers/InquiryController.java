package com.farming_production.farming_production.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.farming_production.farming_production.dto.InquiryDTO;
import com.farming_production.farming_production.dto.NewInquiryDTO;
import com.farming_production.farming_production.services.InquiryService;

@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/inquiry")
@RestController
public class InquiryController {

    private final InquiryService service;
  
    public InquiryController(InquiryService srv){
        this.service =srv;
    }

        /* ================ CREATE ================ */
    @PostMapping()
    public ResponseEntity<InquiryDTO> create(@Valid @RequestBody NewInquiryDTO inquiryDTO){
        InquiryDTO result = service.create(inquiryDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(result);        
    }
    /* ================ RETRIEVE ================ */

    @GetMapping("/{id}")
    public ResponseEntity<InquiryDTO> retrieve(@PathVariable("id") Long id){
        InquiryDTO result = service.retrieve(id);
        return ResponseEntity.ok().body(result);        
    }

    /* ================ LIST ================ */

    @GetMapping() //el verbo es diferente a create ya que va
    public ResponseEntity<List<InquiryDTO>> list(){
        List<InquiryDTO> result  = service.list();
        return ResponseEntity.ok().body(result);        
    }

    /* ================ UPDATE ================ */

    @PutMapping("/{id}")
    public ResponseEntity<InquiryDTO> update(@RequestBody InquiryDTO inquiryDTO, @PathVariable("id") Long id){
        InquiryDTO result = service.update(inquiryDTO, id);
        return ResponseEntity.ok().body(result);
    }

    /* ================ DELETE ================ */
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") Long id){
        service.delete(id);
        return ResponseEntity.ok().body("Inquiry deleted!");        
    }
}