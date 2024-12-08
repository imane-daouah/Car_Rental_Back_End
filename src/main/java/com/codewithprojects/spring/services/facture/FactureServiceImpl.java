package com.codewithprojects.spring.services.facture;
import java.util.Calendar;

import com.codewithprojects.spring.dto.FactureDTO;
import com.codewithprojects.spring.dto.FactureRequest;
import com.codewithprojects.spring.entity.Facture;
import com.codewithprojects.spring.entity.Reservation;
import com.codewithprojects.spring.repository.FactureRepository;
import com.codewithprojects.spring.repository.ReservationRepository;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class FactureServiceImpl implements FactureService {

    private final FactureRepository factureRepository;
    private final ReservationRepository reservationRepository;

    public FactureServiceImpl(FactureRepository factureRepository, ReservationRepository reservationRepository) {
        this.factureRepository = factureRepository;
        this.reservationRepository = reservationRepository;
    }

    @Override
    public List<FactureDTO> getAllFactures() {
        return factureRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public FactureDTO getFactureById(long id) {
        Facture facture = factureRepository.findById(id).orElseThrow(() -> new RuntimeException("Facture not found"));
        return convertToDTO(facture);
    }

    @Override
    public FactureDTO createFacture(FactureRequest factureRequest) {
        Reservation reservation = reservationRepository.findById((int) factureRequest.getId_reservation())
                .orElseThrow(() -> new RuntimeException("Reservation not found"));

        Facture facture = new Facture();
        facture.setReservation(reservation);
        facture.setNum_compte(factureRequest.getNum_compte());
        facture.setDate_paiement(LocalDate.now());

        // Convertir les dates en Calendar
        Calendar startDate = Calendar.getInstance();
        startDate.setTime(reservation.getDate_debut());

        Calendar endDate = Calendar.getInstance();
        endDate.setTime(reservation.getDate_fin());

        // Calculer la différence en jours
        long diffInMillis = endDate.getTimeInMillis() - startDate.getTimeInMillis();
        long daysBetween = diffInMillis / (1000 * 60 * 60 * 24); // Convertir millisecondes en jours

        // Calculer le montant
        facture.setMontant(daysBetween * reservation.getCar().getTarif());

        Facture savedFacture = factureRepository.save(facture);
        return convertToDTO(savedFacture);
    }



    @Override
    public FactureDTO updateFacture(long id, FactureDTO factureDTO) {
        Facture facture = factureRepository.findById(id).orElseThrow(() -> new RuntimeException("Facture not found"));
        facture.setDate_paiement(factureDTO.getDate_paiement());
        facture.setMontant(factureDTO.getMontant());
        facture.setNum_compte(factureDTO.getNum_compte());
        Facture updatedFacture = factureRepository.save(facture);
        return convertToDTO(updatedFacture);
    }

    @Override
    public void deleteFacture(long id) {
        factureRepository.deleteById(id);
    }

    private FactureDTO convertToDTO(Facture facture) {
        FactureDTO factureDTO = new FactureDTO();
        factureDTO.setId_facture(facture.getId_facture());
        factureDTO.setId_reservation(facture.getReservation().getId_reservation());
        factureDTO.setDate_paiement(facture.getDate_paiement());
        factureDTO.setMontant(facture.getMontant());
        factureDTO.setNum_compte(facture.getNum_compte());
        return factureDTO;
    }
}
