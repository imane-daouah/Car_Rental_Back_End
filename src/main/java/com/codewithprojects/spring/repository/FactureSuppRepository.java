package com.codewithprojects.spring.repository;

import com.codewithprojects.spring.entity.Facture;
import com.codewithprojects.spring.entity.FactureSupplementaire;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface FactureSuppRepository extends JpaRepository<FactureSupplementaire, Long> {
    @Query("SELECT SUM(f.montant) FROM Facture f")
    Double sumMontants();
    @Query("SELECT f FROM Facture f where f.reservation.user.id= :clientId")
    List<Facture>getFacturesClient(@Param("clientId") Long clientId);

    // Récupérer les factures dans une période spécifique
    @Query("SELECT f FROM Facture f WHERE f.date_paiement BETWEEN :startDate AND :endDate")
    List<Facture> findFacturesBetweenDates(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);

    // Calculer le total des revenus dans une période spécifique
    @Query("SELECT SUM(f.montant) FROM Facture f WHERE f.date_paiement BETWEEN :startDate AND :endDate")
    Double calculateTotalRevenue(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);

}
