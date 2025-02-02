package com.codewithprojects.spring.services.report;

import com.codewithprojects.spring.entity.Facture;
import com.codewithprojects.spring.entity.Reservation;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Service
public interface ReportService {
    List<Reservation> getReservationsForPeriod(LocalDate startDate, LocalDate endDate);

    List<Facture> getFacturesForPeriod(LocalDate startDate, LocalDate endDate);

    Double getTotalRevenue(LocalDate startDate, LocalDate endDate);

    Map<String, Double> getReservationStatusPercentages();

}
