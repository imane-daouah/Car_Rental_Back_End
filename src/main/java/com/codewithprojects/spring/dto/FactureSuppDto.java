package com.codewithprojects.spring.dto;

import com.codewithprojects.spring.entity.Facture;
import lombok.Data;

import java.time.LocalDate;

@Data

public class FactureSuppDto {
    private long id_facture;
    private Facture facture;
    private LocalDate date;
    private String supplementDetails;
    private Double montantSupp;
}
