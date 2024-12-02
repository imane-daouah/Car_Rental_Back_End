package com.codewithprojects.spring.controller;


import com.codewithprojects.spring.dto.FactureDTO;
import com.codewithprojects.spring.dto.FactureRequest;
import com.codewithprojects.spring.services.facture.FactureService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/factures")
public class FactureController {

    private final FactureService factureService;

    public FactureController(FactureService factureService) {
        this.factureService = factureService;
    }

    @GetMapping
    public List<FactureDTO> getAllFactures() {
        return factureService.getAllFactures();
    }

    @GetMapping("/{id}")
    public FactureDTO getFactureById(@PathVariable long id) {
        return factureService.getFactureById(id);
    }

    @PostMapping
    public FactureDTO createFacture(@RequestBody FactureRequest factureRequest) {
        return factureService.createFacture(factureRequest);
    }

    @PutMapping("/{id}")
    public FactureDTO updateFacture(@PathVariable long id, @RequestBody FactureDTO factureDTO) {
        return factureService.updateFacture(id, factureDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteFacture(@PathVariable long id) {
        factureService.deleteFacture(id);
    }
}
