package com.farming_production.farming_production.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.farming_production.farming_production.dto.SupplyDTO;
import com.farming_production.farming_production.dto.NewSupplyDTO;
import com.farming_production.farming_production.services.SupplyService;

@RequestMapping("/supply")
@RestController
public class SupplyController {

    private final SupplyService service;
  
    @Autowired
    public SupplyController(SupplyService srv){
        this.service =srv;
    }

    @PostMapping()
    public ResponseEntity<SupplyDTO> create(@Valid @RequestBody NewSupplyDTO supplyDTO){
        SupplyDTO result = service.create(supplyDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(result);        
    }

    @GetMapping("/{id}")
    public ResponseEntity<SupplyDTO> retrive(@PathVariable("id") Long id){
        SupplyDTO result = service.retrieve(id);
        return ResponseEntity.ok().body(result);        
    }

    @GetMapping() //el verbo es diferente a create ya que va
    public ResponseEntity<List<SupplyDTO>> list(){
        List<SupplyDTO> result  = service.list();
        return ResponseEntity.ok().body(result);        
    }

    @PutMapping("/{id}")
    public ResponseEntity<SupplyDTO> update(@RequestBody SupplyDTO supplyDTO, @PathVariable("id") Long id){
        SupplyDTO result = service.update(supplyDTO, id);
        return ResponseEntity.ok().body(result);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") Long id){
        service.delete(id);
        return ResponseEntity.ok().body("Supply deleted!");        
    }
}