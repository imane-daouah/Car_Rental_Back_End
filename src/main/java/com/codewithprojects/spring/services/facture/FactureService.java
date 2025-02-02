package com.codewithprojects.spring.services.facture;

import java.io.IOException;
import java.util.List;

import com.codewithprojects.spring.dto.FactureDto;
import com.codewithprojects.spring.dto.FactureRequest;
import com.codewithprojects.spring.dto.FactureSuppDto;
import com.codewithprojects.spring.entity.Contrat;
import org.springframework.stereotype.Service;


@Service
public interface FactureService {
    List<FactureDto> getAllFactures();
    FactureDto getFactureById(long id);
    FactureSuppDto getFactureSuppById(long id);
    FactureDto createFacture(FactureRequest factureRequest);
    // FactureDto createFacture(Long id);

    FactureDto updateFacture(long id, FactureDto factureDTO);
    void deleteFacture(long id);

    double getSommeMontantsFactures();
    byte[] generateFacturePDF( FactureDto paiement)throws IOException;
    List<FactureDto> getAllFacturesClient(Long id);
    FactureSuppDto createFactureSupplimentaire(Contrat contrat ,Double frait, Double montant,String detail);
    byte[] generateFactureSupplimentairePDF(FactureSuppDto paiement) throws IOException;


}