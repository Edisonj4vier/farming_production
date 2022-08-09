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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.farming_production.farming_production.dto.SupplyDTO;
import com.farming_production.farming_production.dto.NewSupplyDTO;
import com.farming_production.farming_production.services.SupplyService;

@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/products")
@RestController
public class SupplyController {

    private final SupplyService service;

    public SupplyController(SupplyService srv) {
        this.service = srv;
    }

    // (Long idProduct, Long idMaintenance, List<NewSupplyDTO> supplies)
    /* ================ CREATE ================ */
    @PostMapping("/{id}/maintenances/{idMaintenance}/supplies")
    public ResponseEntity<List<SupplyDTO>> create(@PathVariable("id") Long id,
            @PathVariable("idQuestion") Long idQuestion, @Valid @RequestBody List<NewSupplyDTO> suppliesDTO) {
        List<SupplyDTO> optionDTOs = service.create(id, idQuestion, suppliesDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(optionDTOs);
    }

    /* ================ DELETE ================ */
    @DeleteMapping("/{id}/maintenances/{idMaintenance}/supplies")
    public ResponseEntity<List<SupplyDTO>> delete(@PathVariable("id") Long id,
            @PathVariable("idQuestion") Long idQuestion) {
        service.remove(id, idQuestion);
        return ResponseEntity.noContent().build();
    }

    /* ================ LIST ================ */
    @GetMapping("/{id}/maintenances/{idMaintenance}/supplies")
    public ResponseEntity<List<SupplyDTO>> list(@PathVariable("id") Long id,
            @PathVariable("idMaintenance") Long idQuestion) {
        List<SupplyDTO> optionDTOs = service.list(id, idQuestion);
        return ResponseEntity.status(HttpStatus.OK).body(optionDTOs);
    }
}