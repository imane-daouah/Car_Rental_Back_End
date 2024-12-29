package com.codewithprojects.spring.entity;
import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;

@Entity
@Data
@Table(name = "facture")
public class Facture {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id_facture;

    @ManyToOne
    @JoinColumn(name = "id_reservation", nullable = false)
    private Reservation reservation;

    private LocalDate date_paiement;

    private double montant;

    @Column(name = "num_compte", nullable = false)
    private String num_compte;


}