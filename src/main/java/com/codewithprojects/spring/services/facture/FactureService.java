package com.codewithprojects.spring.services.facture;


import com.codewithprojects.spring.dto.FactureDTO;
import com.codewithprojects.spring.dto.FactureRequest;

import java.util.List;

public interface FactureService {
    List<FactureDTO> getAllFactures();
    FactureDTO getFactureById(long id);
    FactureDTO createFacture(FactureRequest factureRequest);
    FactureDTO updateFacture(long id, FactureDTO factureDTO);
    void deleteFacture(long id);
}
