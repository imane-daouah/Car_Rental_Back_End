package com.codewithprojects.spring.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name="cars")
public class Car {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    private String marque ;
    private Integer modele;
    private Integer annee;
    private String type ;
    private Double tarif ;
    private  String etat;
    private String image ;
    private String description;

    public Long getId() {
        return id;
    }

    public String getMarque() {
        return marque;
    }

    public Integer getModele() {
        return modele;
    }

    public Integer getAnnee() {
        return annee;
    }

    public String getType() {
        return type;
    }

    public Double getTarif() {
        return tarif;
    }

    public String getEtat() {
        return etat;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setMarque(String marque) {
        this.marque = marque;
    }

    public void setModele(Integer modele) {
        this.modele = modele;
    }

    public void setAnnee(Integer annee) {
        this.annee = annee;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setTarif(Double tarif) {
        this.tarif = tarif;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    public String getImage() {
        return image;
    }

    public String getDescription() {
        return description;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
