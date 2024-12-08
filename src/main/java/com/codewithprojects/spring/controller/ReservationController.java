package com.codewithprojects.spring.controller;

import com.codewithprojects.spring.dto.ReservationRequest;

import com.codewithprojects.spring.dto.ReservationDto;
import com.codewithprojects.spring.dto.ReservationRequest;
import com.codewithprojects.spring.services.reservations.ReservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reservations")
@RequiredArgsConstructor
public class ReservationController {

    private final ReservationService reservationService;

    // Créer une nouvelle réservation
    @PostMapping("/creer")
    public ResponseEntity<ReservationDto> createReservation(@RequestBody ReservationRequest reservationRequest) {
        ReservationDto createdReservation = reservationService.creerReservation(reservationRequest);
        return ResponseEntity.ok(createdReservation);
    }

    // Modifier une réservation existante
    @PutMapping("/modifier/{id}")
    public ResponseEntity<ReservationDto> updateReservation(
            @PathVariable int id,
            @RequestBody ReservationRequest reservationRequest) {
        ReservationDto updatedReservation = reservationService.modifierReservation(id, reservationRequest);
        return ResponseEntity.ok(updatedReservation);
    }

    // Supprimer une réservation par ID
    @DeleteMapping("/supprimer/{id}")
    public ResponseEntity<Void> deleteReservation(@PathVariable int id) {
        reservationService.supprimerReservation(id);
        return ResponseEntity.noContent().build();
    }

    // Obtenir toutes les réservations
    @GetMapping("/toutes")
    public ResponseEntity<List<ReservationDto>> getAllReservations() {
        List<ReservationDto> reservations = reservationService.getToutesLesReservations();
        return ResponseEntity.ok(reservations);
    }

    // Obtenir une réservation par ID
    @GetMapping("/{id}")
    public ResponseEntity<ReservationDto> getReservationById(@PathVariable int id) {
        ReservationDto reservation = reservationService.getReservationById(id);
        return ResponseEntity.ok(reservation);
    }
}
