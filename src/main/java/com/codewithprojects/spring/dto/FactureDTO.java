package com.codewithprojects.spring.dto;


import lombok.Data;
import java.time.LocalDate;

@Data
public class FactureDTO {
    private long id_facture;
    private long id_reservation;
    private LocalDate date_paiement;
    private double montant;
    private String num_compte;

    public long getId_facture() {
        return id_facture;
    }

    public long getId_reservation() {
        return id_reservation;
    }

    public LocalDate getDate_paiement() {
        return date_paiement;
    }

    public double getMontant() {
        return montant;
    }

    public String getNum_compte() {
        return num_compte;
    }

    public void setId_facture(long id_facture) {
        this.id_facture = id_facture;
    }

    public void setId_reservation(long id_reservation) {
        this.id_reservation = id_reservation;
    }

    public void setDate_paiement(LocalDate date_paiement) {
        this.date_paiement = date_paiement;
    }

    public void setMontant(double montant) {
        this.montant = montant;
    }

    public void setNum_compte(String num_compte) {
        this.num_compte = num_compte;
    }
}
