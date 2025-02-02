package com.codewithprojects.spring.services.report;

import com.codewithprojects.spring.entity.Facture;
import com.codewithprojects.spring.entity.Reservation;
import com.codewithprojects.spring.repository.FactureRepository;
import com.codewithprojects.spring.repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ReportServiceImpl implements ReportService {
    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private FactureRepository factureRepository;

    // Récupérer les réservations dans une période donnée
    public List<Reservation> getReservationsForPeriod(LocalDate startDate, LocalDate endDate) {
        List<String> statuses = Arrays.asList("Terminé", "En cours", "Confirmé");
        return reservationRepository.findReservationsBetweenDatesAndStatuses(startDate, endDate,statuses);
    }

    // Récupérer les factures dans une période donnée
    public List<Facture> getFacturesForPeriod(LocalDate startDate, LocalDate endDate) {
        return factureRepository.findFacturesBetweenDates(startDate, endDate);
    }

    // Calculer le revenu total dans une période donnée
    public Double getTotalRevenue(LocalDate startDate, LocalDate endDate) {
        return factureRepository.calculateTotalRevenue(startDate, endDate);
    }

    public Map<String, Double> getReservationStatusPercentages() {
        List<Object[]> results = reservationRepository.countReservationsByStatus();

        // Calcul du total des réservations
        long totalReservations = results.stream()
                .mapToLong(result -> (long) result[1]) // Le deuxième élément est le count
                .sum();

        // Calcul des pourcentages
        return results.stream()
                .collect(Collectors.toMap(
                        result -> (String) result[0], // Le premier élément est le statut
                        result -> ((double) (long) result[1] / totalReservations) * 100
                ));
    }

}
