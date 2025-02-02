package com.codewithprojects.spring.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "contrats")
public class Contrat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    private Long id;

    @OneToOne
    @JoinColumn(name = "id_reservation", nullable = false)
    private Reservation reservation;

    private String etat;
    @Lob
    private byte[] signatureImage;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Reservation getReservation() {
        return reservation;
    }

    public void setReservation(Reservation reservation) {
        this.reservation = reservation;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    public byte[] getSignatureImage() {
        return signatureImage;
    }

    public void setSignatureImage(byte[] signatureImage) {
        this.signatureImage = signatureImage;
    }

}
