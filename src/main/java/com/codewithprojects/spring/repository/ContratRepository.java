package com.codewithprojects.spring.repository;

import com.codewithprojects.spring.entity.Contrat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ContratRepository extends JpaRepository<Contrat, Long> {
    @Query("SELECT c FROM Contrat c WHERE c.reservation.id_resrvation = :reservationId")
    Contrat contratByreservationId(@Param("reservationId") Long reservationId);
}
