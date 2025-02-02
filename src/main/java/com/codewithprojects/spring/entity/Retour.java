package com.codewithprojects.spring.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Data
@Table(name = "retour")
public class Retour {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id_retour;

    @OneToOne
    @JoinColumn(name = "id_contrat", nullable = false)
    private Contrat contrat;

    private LocalDate date_retour;

    private Boolean etat_voitureBoolean;

    public long getId_retour() {
        return id_retour;
    }

    public void setId_retour(long id_retour) {
        this.id_retour = id_retour;
    }

    public Contrat getContrat() {
        return contrat;
    }

    public void setContrat(Contrat contrat) {
        this.contrat = contrat;
    }

    public LocalDate getDate_retour() {
        return date_retour;
    }

    public void setDate_retour(LocalDate date_retour) {
        this.date_retour = date_retour;
    }

    public Boolean getEtat_voitureBoolean() {
        return etat_voitureBoolean;
    }

    public void setEtat_voitureBoolean(Boolean etat_voitureBoolean) {
        this.etat_voitureBoolean = etat_voitureBoolean;
    }



}
