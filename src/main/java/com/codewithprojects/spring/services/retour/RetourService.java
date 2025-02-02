package com.codewithprojects.spring.services.retour;

import com.codewithprojects.spring.dto.RetourDto;

import java.util.List;

public interface RetourService {
    List<RetourDto> getAllRetour();
    RetourDto ajouterRetour(Long id_contrat, Boolean etet);
}
