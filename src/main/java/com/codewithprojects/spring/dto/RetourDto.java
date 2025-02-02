package com.codewithprojects.spring.dto;

import com.codewithprojects.spring.entity.Contrat;
import lombok.Data;

import java.time.LocalDate;

@Data
public class RetourDto {
    private long id_retour;
    private Contrat contrat;
    private LocalDate date_retour;
    private Boolean etat_voitureBoolean;
}
