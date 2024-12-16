package com.codewithprojects.spring.controller;


import com.codewithprojects.spring.dto.FactureDto;
import com.codewithprojects.spring.dto.FactureRequest;
import com.codewithprojects.spring.services.facture.FactureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;


import java.util.List;
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/factures")
public class FactureController {
	@Autowired
    private final FactureService factureService;

    public FactureController(FactureService factureService) {
        this.factureService = factureService;
    }

    @GetMapping("/tous")
    public List<FactureDto> getAllFactures() {
        return factureService.getAllFactures();
    }

    @GetMapping("/{id}")
    public FactureDto getFactureById(@PathVariable long id) {
        return factureService.getFactureById(id);
    }

    @PostMapping("/creer")
    public FactureDto createFacture(@RequestBody FactureRequest factureRequest) {
        return factureService.createFacture(factureRequest);
    }

    @PutMapping("modifier/{id}")
    public FactureDto updateFacture(@PathVariable long id, @RequestBody FactureDto factureDTO) {
        return factureService.updateFacture(id, factureDTO);
    }

    @DeleteMapping("supprimer/{id}")
    public void deleteFacture(@PathVariable long id) {
        factureService.deleteFacture(id);
    }
    @GetMapping("/somme")
    public double getSommeMontants() {
        return factureService.getSommeMontantsFactures();
    }
    @GetMapping("/download/{idPaiement}")
    public ResponseEntity<byte[]> downloadFacture(@PathVariable Long idPaiement) throws Exception {
        FactureDto paiement = factureService.getFactureById(idPaiement); // Récupérer le paiement depuis la base de données
        byte[] pdf = factureService.generateFacturePDF(paiement);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/pdf");
        headers.add("Content-Disposition", "attachment; filename=facture_" + idPaiement + ".pdf");

        return new ResponseEntity<>(pdf, headers, HttpStatus.OK);
    }
}