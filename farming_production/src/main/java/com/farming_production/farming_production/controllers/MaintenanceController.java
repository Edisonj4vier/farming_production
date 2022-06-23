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

import com.farming_production.farming_production.dto.MaintenanceDTO;
import com.farming_production.farming_production.dto.NewMaintenanceDTO;
import com.farming_production.farming_production.services.MaintenanceService;

@RequestMapping("/maintenance")
@RestController
public class MaintenanceController {

    private final MaintenanceService service;
  
    @Autowired
    public MaintenanceController(MaintenanceService srv){
        this.service =srv;
    }

    @PostMapping()
    public ResponseEntity<MaintenanceDTO> create(@Valid @RequestBody NewMaintenanceDTO maintenanceDTO){
        MaintenanceDTO result = service.create(maintenanceDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(result);        
    }

    @GetMapping("/{id}")
    public ResponseEntity<MaintenanceDTO> retrieve(@PathVariable("id") Long id){
        MaintenanceDTO result = service.retrieve(id);
        return ResponseEntity.ok().body(result);        
    }

    @GetMapping() //el verbo es diferente a create ya que va
    public ResponseEntity<List<MaintenanceDTO>> list(){
        List<MaintenanceDTO> result  = service.list();
        return ResponseEntity.ok().body(result);        
    }

    @PutMapping("/{id}")
    public ResponseEntity<MaintenanceDTO> update(@RequestBody MaintenanceDTO maintenanceDTO, @PathVariable("id") Long id){
        MaintenanceDTO result = service.update(maintenanceDTO, id);
        return ResponseEntity.ok().body(result);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") Long id){
        service.delete(id);
        return ResponseEntity.ok().body("Maintenance deleted!");        
    }
}