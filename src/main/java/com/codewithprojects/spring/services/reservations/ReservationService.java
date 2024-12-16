package com.codewithprojects.spring.services.reservations;

import com.codewithprojects.spring.dto.ReservationDto;
import com.codewithprojects.spring.dto.ReservationRequest;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public interface ReservationService {
    ReservationDto creerReservation(ReservationRequest reservationRequest);
    ReservationDto modifierReservation(int id, ReservationRequest reservationRequest);
    void supprimerReservation(int id);
    List<ReservationDto> getToutesLesReservations();
    ReservationDto getReservationById(int id);
    long getNombreResrvation();
}