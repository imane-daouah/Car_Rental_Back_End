package com.codewithprojects.spring.repository;

import com.codewithprojects.spring.entity.Car;
import com.codewithprojects.spring.entity.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    @Query("SELECT COUNT(r) FROM Reservation r WHERE r.user.id = :clientId")
    long countByClientId(@Param("clientId") Long clientId);
    @Query("SELECT COUNT(r) FROM Reservation r WHERE r.user.id = :clientId and r.statu = :statu")
    long countByClientIdAndStatu(@Param("clientId") Long clientId,@Param("statu") String statu);
    @Query("SELECT r FROM Reservation r WHERE r.user.id = :clientId ")
    List<Reservation> reservationClientId(@Param("clientId") Long clientId);

    @Query("SELECT r FROM Reservation r WHERE r.date_de_reservation BETWEEN :startDate AND :endDate AND r.statu IN :statuses")
    List<Reservation> findReservationsBetweenDatesAndStatuses(
            @Param("startDate") LocalDate startDate,
            @Param("endDate") LocalDate endDate,
            @Param("statuses") List<String> statuses
    );

    @Query("SELECT r.statu, COUNT(r) FROM Reservation r GROUP BY r.statu")
    List<Object[]> countReservationsByStatus();


    @Query("SELECT COUNT(r) > 0 FROM Reservation r WHERE r.car = :car AND r.statu = :statu AND " +
            "(r.date_debut <= :dateFin AND r.date_fin >= :dateDebut)")
    boolean existsByCarAndStatuAndDatesOverlap(
            @Param("car") Car car,
            @Param("statu") String statu,
            @Param("dateDebut") Date dateDebut,
            @Param("dateFin") Date dateFin);

    @Query("SELECT COUNT(r) > 0 FROM Reservation r WHERE r.car = :car AND r.statu IN :status AND " +
            "(r.date_debut <= :dateFin AND r.date_fin >= :dateDebut)")
    boolean existsByCarAndStatuInAndDatesOverlap(
            @Param("car") Car car,
            @Param("status") List<String> status,
            @Param("dateDebut") Date dateDebut,
            @Param("dateFin") Date dateFin);

}
