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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.farming_production.farming_production.dto.ProductDTO;
import com.farming_production.farming_production.dto.NewProductDTO;
import com.farming_production.farming_production.services.ProductService;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductService service;

    public ProductController(ProductService srv) {
        this.service = srv;
    }

    /* ================ CREATE ================ */
    @PostMapping()
    public ResponseEntity<ProductDTO> create(@Valid @RequestBody NewProductDTO productDTO) {
        ProductDTO result = service.create(productDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }

    /* ================ RETRIEVE ================ */

    @GetMapping("/{id}")
    public ResponseEntity<ProductDTO> retrieve(@PathVariable("id") Long id) {
        ProductDTO result = service.retrieve(id);
        return ResponseEntity.ok().body(result);
    }

    /* ================ UPDATE ================ */
    @PutMapping("/{id}")
    public ResponseEntity<ProductDTO> update(@RequestBody ProductDTO productDTO, @PathVariable("id") Long id) {
        ProductDTO result = service.update(productDTO, id);
        return ResponseEntity.ok().body(result);
    }

    /* ================ DELETE ================ */
    @DeleteMapping("/{id}")
    public ResponseEntity<String> remove(@PathVariable("id") Long id) {
        service.remove(id);
        return ResponseEntity.ok().body("Product deleted!");
    }

    /* ================ LIST ================ */
    @GetMapping("/{page}/{size}")
    public ResponseEntity<List<ProductDTO>> list(@PathVariable("page") int page,
            @PathVariable("size") int size, 
            @RequestParam(name = "sort", required = false) String sort) {
        List<ProductDTO> result = service.list(page, size, sort);
        return ResponseEntity.ok().body(result);
    }

    /* ================ COUNT ================ */
    @GetMapping("/count")
    public ResponseEntity<Long> count() {
        long result = service.count();
        return ResponseEntity.ok().body(result);
    }

}