package com.farming_production.farming_production.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.CrossOrigin;
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

@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/products")
@RestController
public class SupplyController {

    private final SupplyService service;

    public SupplyController(SupplyService srv) {
        this.service = srv;
    }

    /* ================ CREATE ================ */
    @Secured({"ROLE_AGRICULTURAL_ENGINEER"})
    @PostMapping("/{idProduct}/supplies")
    public ResponseEntity<SupplyDTO> create(@PathVariable("idProduct") long idProduct,
            @Valid @RequestBody NewSupplyDTO supplyDTO) {
        SupplyDTO result = service.create(idProduct, supplyDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }
    /* ================ RETRIEVE ================ */
    @Secured({"ROLE_AGRICULTURAL_ENGINEER" , "ROLE_WORKER"})
    @GetMapping("/{idProduct}/supplies/{id}")
    public ResponseEntity<SupplyDTO> retrieve(@PathVariable("idProduct") Long idProduct,
            @PathVariable("id") Long id) {
        SupplyDTO result = service.retrieve(idProduct, id);
        return ResponseEntity.ok().body(result);
    }

    /* ================ LIST ================ */
    @Secured({"ROLE_AGRICULTURAL_ENGINEER" , "ROLE_WORKER"})
    @GetMapping("/{idProduct}/supplies")
    public ResponseEntity<List<SupplyDTO>> list(@PathVariable("idProduct") Long idProduct) {
        List<SupplyDTO> supplies = service.list(idProduct);
        return ResponseEntity.ok().body(supplies);
    }

    /* ================ UPDATE ================ */
    @Secured({"ROLE_AGRICULTURAL_ENGINEER"})
    @PutMapping("/{idProduct}/supplies/{id}")
    public ResponseEntity<SupplyDTO> update(@RequestBody SupplyDTO supplyDTO,
            @PathVariable("idProduct") Long idProduct,
            @PathVariable("id") Long id) {

        SupplyDTO result = service.update(supplyDTO, idProduct, id);
        return ResponseEntity.ok().body(result);
    }

    /* ================ DELETE ================ */
    @Secured({"ROLE_AGRICULTURAL_ENGINEER"})
    @DeleteMapping("/{idProduct}/supplies/{id}")
    public ResponseEntity<Void> delete(@PathVariable("idProduct") Long idProduct, @PathVariable("id") Long id) {
        service.delete(idProduct, id);
        return ResponseEntity.noContent().build();
    }

}