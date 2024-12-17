package com.codewithprojects.spring.services.facture;

import java.io.IOException;
import java.util.List;

import com.codewithprojects.spring.dto.FactureDto;
import com.codewithprojects.spring.dto.FactureRequest;
import org.springframework.stereotype.Service;


@Service
public interface FactureService {
    List<FactureDto> getAllFactures();
    FactureDto getFactureById(long id);
    FactureDto createFacture(FactureRequest factureRequest);
    FactureDto updateFacture(long id, FactureDto factureDTO);
    void deleteFacture(long id);
    
    double getSommeMontantsFactures();
    byte[] generateFacturePDF( FactureDto paiement)throws IOException;
}