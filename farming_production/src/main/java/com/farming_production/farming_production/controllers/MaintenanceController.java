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

import com.farming_production.farming_production.dto.MaintenanceDTO;
import com.farming_production.farming_production.dto.NewMaintenanceDTO;
import com.farming_production.farming_production.services.MaintenanceService;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/products")
public class MaintenanceController {

    final MaintenanceService service;

    public MaintenanceController(MaintenanceService srv) {
        this.service = srv;
    }

    /* ================ CREATE ================ */
    @PostMapping("/{idProduct}/maintenances")
    public ResponseEntity<MaintenanceDTO> create(@PathVariable("idProduct") long idProduct,
            @Valid @RequestBody NewMaintenanceDTO maintenanceDTO) {
        MaintenanceDTO result = service.create(idProduct, maintenanceDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }
    /* ================ RETRIEVE ================ */

    @GetMapping("/{idProduct}/maintenances/{id}")
    public ResponseEntity<MaintenanceDTO> retrieve(@PathVariable("idProduct") Long idProduct,
            @PathVariable("id") Long id) {
        MaintenanceDTO result = service.retrieve(idProduct, id);
        return ResponseEntity.ok().body(result);
    }

    /* ================ LIST ================ */
    @GetMapping("/{idProduct}/maintenances")
    public ResponseEntity<List<MaintenanceDTO>> list(@PathVariable("idProduct") Long idProduct) {
        List<MaintenanceDTO> maintenances = service.list(idProduct);
        return ResponseEntity.ok().body(maintenances);
    }

    /* ================ UPDATE ================ */
    @PutMapping("/{idProduct}/maintenances/{id}")
    public ResponseEntity<MaintenanceDTO> update(@RequestBody MaintenanceDTO maintenanceDTO,
            @PathVariable("idProduct") Long idProduct,
            @PathVariable("id") Long id) {

        MaintenanceDTO result = service.update(maintenanceDTO, idProduct, id);
        return ResponseEntity.ok().body(result);
    }

    /* ================ DELETE ================ */
    @DeleteMapping("/{idProduct}/maintenances/{id}")
    public ResponseEntity<Void> delete(@PathVariable("idProduct") Long idProduct, @PathVariable("id") Long id) {
        service.delete(idProduct, id);
        return ResponseEntity.noContent().build();
    }

}