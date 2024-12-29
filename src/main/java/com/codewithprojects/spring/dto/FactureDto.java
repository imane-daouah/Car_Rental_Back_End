package com.codewithprojects.spring.dto;
import com.codewithprojects.spring.entity.Reservation;
import lombok.Data;
import java.time.LocalDate;
@Data
public class FactureDto {
    private long id_facture;
    private Reservation reservation; ;
    private LocalDate date_paiement;
    private double montant;
    private String num_compte;

    
    
}