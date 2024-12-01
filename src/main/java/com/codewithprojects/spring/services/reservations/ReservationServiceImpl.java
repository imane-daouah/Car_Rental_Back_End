package com.codewithprojects.spring.services.reservations;
import com.codewithprojects.spring.dto.ReservationDto;
import com.codewithprojects.spring.dto.ReservationRequest;
import com.codewithprojects.spring.entity.Car;
import com.codewithprojects.spring.entity.Reservation;
import com.codewithprojects.spring.entity.User;
import com.codewithprojects.spring.repository.CarsRepository;
import com.codewithprojects.spring.repository.ReservationRepository;
import com.codewithprojects.spring.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReservationServiceImpl implements ReservationService {

    private final ReservationRepository reservationRepository;
    private final CarsRepository carsRepository;
    private final UserRepository userRepository;

    @Override
    public ReservationDto creerReservation(ReservationRequest reservationRequest) {
        // Vérifiez si la voiture et l'utilisateur existent
        Car car = carsRepository.findById((long) reservationRequest.getCarId())
                .orElseThrow(() -> new RuntimeException("Voiture introuvable avec l'ID " + reservationRequest.getCarId()));
        User user = userRepository.findById((long) reservationRequest.getUserId())
                .orElseThrow(() -> new RuntimeException("Utilisateur introuvable avec l'ID " + reservationRequest.getUserId()));

        Reservation reservation = new Reservation();
        reservation.setDate_debut(reservationRequest.getDate_debut());
        reservation.setDate_fin(reservationRequest.getDate_fin());
        reservation.setStatus("En attente");
        reservation.setCar(car);
        reservation.setUser(user);

        Reservation reservationCree = reservationRepository.save(reservation);

        return convertirEnDto(reservationCree);
    }

    @Override
    public ReservationDto modifierReservation(int id, ReservationRequest reservationRequest) {
        // Trouver la réservation existante
        Reservation reservationExistante = reservationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Réservation introuvable avec l'ID " + id));

        // Modifier uniquement les champs nécessaires
        reservationExistante.setDate_debut(reservationRequest.getDate_debut());
        reservationExistante.setDate_fin(reservationRequest.getDate_fin());
        reservationExistante.setStatus(reservationRequest.getStatus());

        // Laisser les champs `car` et `user` inchangés
        reservationExistante.setCar(reservationExistante.getCar());
        reservationExistante.setUser(reservationExistante.getUser());

        // Sauvegarder les modifications
        Reservation reservationModifiee = reservationRepository.save(reservationExistante);

        // Retourner le DTO
        return convertirEnDto(reservationModifiee);
    }



    @Override
    public void supprimerReservation(int id) {
        reservationRepository.deleteById(id);
    }

    @Override
    public List<ReservationDto> getToutesLesReservations() {
        return reservationRepository.findAll().stream()
                .map(this::convertirEnDto)
                .collect(Collectors.toList());
    }

    @Override
    public ReservationDto getReservationById(int id) {
        Reservation reservation = reservationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Réservation introuvable avec l'ID " + id));
        return convertirEnDto(reservation);
    }

    private ReservationDto convertirEnDto(Reservation reservation) {
        ReservationDto reservationDto = new ReservationDto();
        reservationDto.setId_reservation(reservation.getId_reservation());
        reservationDto.setDate_debut(reservation.getDate_debut());
        reservationDto.setDate_fin(reservation.getDate_fin());
        reservationDto.setStatus(reservation.getStatus());
        reservationDto.setCar(reservation.getCar());
        reservationDto.setUser(reservation.getUser());
        return reservationDto;
    }
}
