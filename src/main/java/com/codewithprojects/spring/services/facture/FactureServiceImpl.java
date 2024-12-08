package com.codewithprojects.spring.services.facture;


import com.codewithprojects.spring.dto.FactureDTO;
import com.codewithprojects.spring.dto.FactureRequest;
import com.codewithprojects.spring.entity.Facture;
import com.codewithprojects.spring.entity.Reservation;
import com.codewithprojects.spring.repository.FactureRepository;
import com.codewithprojects.spring.repository.ReservationRepository;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;


import org.springframework.beans.factory.annotation.Autowired;

@Service
public class FactureServiceImpl implements FactureService {
    @Autowired
    private  FactureRepository factureRepository;
    @Autowired
    private  ReservationRepository reservationRepository;


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
        Reservation reservation = reservationRepository.findById(factureRequest.getId_reservation())
                .orElseThrow(() -> new RuntimeException("Reservation not found"));

        Facture facture = new Facture();
        facture.setReservation(reservation);
        facture.setNum_compte(factureRequest.getNum_compte());
        facture.setDate_paiement(LocalDate.now());

        // Convertir les dates en objets Calendar
        Calendar startCalendar = Calendar.getInstance();
        startCalendar.setTime(Date.from(reservation.getDate_debut().toInstant()));

        Calendar endCalendar = Calendar.getInstance();
        endCalendar.setTime(Date.from(reservation.getDate_fin().toInstant()));

        // Calculer la différence en jours entre les deux dates
        long millisBetween = endCalendar.getTimeInMillis() - startCalendar.getTimeInMillis();
        long daysBetween = millisBetween / (1000 * 60 * 60 * 24);

        facture.setMontant(daysBetween * reservation.getCar().getTarif());

        Facture savedFacture = factureRepository.save(facture);
        reservation.setStatus("Termine");
        reservationRepository.save(reservation);
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