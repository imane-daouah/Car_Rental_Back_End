package com.codewithprojects.spring.controller;

import com.codewithprojects.spring.entity.Facture;
import com.codewithprojects.spring.entity.Reservation;
import com.codewithprojects.spring.services.report.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/reports")
public class ReportController {

    @Autowired
    private ReportService reportService;



    // Endpoint pour récupérer les réservations
    @GetMapping("/reservations")
    public List<Reservation> getReservations(@RequestParam String startDate, @RequestParam String endDate) {
        LocalDate start = LocalDate.parse(startDate);
        LocalDate end = LocalDate.parse(endDate);
        return reportService.getReservationsForPeriod(start, end);
    }


    // Endpoint pour récupérer les factures
    @GetMapping("/factures")
    public List<Facture> getFactures(@RequestParam String startDate, @RequestParam String endDate) {
        LocalDate start = LocalDate.parse(startDate);
        LocalDate end = LocalDate.parse(endDate);
        return reportService.getFacturesForPeriod(start, end);
    }

    // Endpoint pour récupérer le revenu total
    @GetMapping("/revenue")
    public Double getRevenue(@RequestParam String startDate, @RequestParam String endDate) {
        LocalDate start = LocalDate.parse(startDate);
        LocalDate end = LocalDate.parse(endDate);
        return reportService.getTotalRevenue(start, end);
    }
    @GetMapping
    public Map<String, Object> getReportData(@RequestParam String startDate, @RequestParam String endDate) {

        //  List<Facture> factures = factureRepository.findAll();
        LocalDate start = LocalDate.parse(startDate);
        LocalDate end = LocalDate.parse(endDate);
        List<Reservation> reservations = reportService.getReservationsForPeriod(start, end);
        List<Facture> factures = reportService.getFacturesForPeriod(start, end);
        Map<String, Object> reportData = new HashMap<>();

        // Vous pouvez ajuster la logique selon la façon dont vous voulez organiser les données
        List<Map<String, Object>> revenueData = factures.stream()
                .map(facture -> {
                    Map<String, Object> data = new HashMap<>();
                    data.put("date", facture.getDate_paiement());
                    data.put("amount", facture.getMontant());
                    return data;
                })
                .collect(Collectors.toList());

        List<Map<String, Object>> reservationData = reservations.stream()
                .collect(Collectors.groupingBy(
                        Reservation::getDate_de_reservation, // Grouper par date de réservation
                        Collectors.counting()                // Compter les réservations par date
                ))
                .entrySet()
                .stream()
                .map(entry -> {
                    Map<String, Object> data = new HashMap<>();
                    data.put("date", entry.getKey());      // La date de réservation
                    data.put("count", entry.getValue());  // Nombre de réservations pour cette date
                    return data;
                })
                .collect(Collectors.toList());

        reportData.put("revenues", revenueData);
        reportData.put("reservations", reservationData);

        return reportData;
    }

    @GetMapping("/pourcentage")
    public Map<String, Object> getPourcentage() {
        // Appel du service pour obtenir les pourcentages
        Map<String, Double> percentages = reportService.getReservationStatusPercentages();

        // Création d'une réponse structurée
        Map<String, Object> response = new HashMap<>();
        response.put("labels", percentages.keySet()); // Les statuts
        response.put("data", percentages.values());  // Les pourcentages correspondants

        return response; // Renvoie la réponse sous forme JSON
    }
}
