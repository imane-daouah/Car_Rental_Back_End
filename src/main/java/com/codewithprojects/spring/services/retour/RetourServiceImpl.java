package com.codewithprojects.spring.services.retour;

import com.codewithprojects.spring.dto.RetourDto;
import com.codewithprojects.spring.entity.Car;
import com.codewithprojects.spring.entity.Contrat;
import com.codewithprojects.spring.entity.Reservation;
import com.codewithprojects.spring.entity.Retour;
import com.codewithprojects.spring.repository.CarsRespository;
import com.codewithprojects.spring.repository.ContratRepository;
import com.codewithprojects.spring.repository.ReservationRepository;
import com.codewithprojects.spring.repository.RetourRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RetourServiceImpl implements RetourService {
    @Autowired
    private RetourRepository retourRepository;
    @Autowired
    private ContratRepository contratRepository;
    @Autowired
    private ReservationRepository reservationRepository;
    @Autowired
    private CarsRespository carsRespository;
    @Override
    public List<RetourDto> getAllRetour() {

        return retourRepository.findAll().stream()
                .map(this::convertDto)
                .collect(Collectors.toList());
    }

    @Override
    public RetourDto ajouterRetour(Long id_contrat,Boolean etat) {
        Contrat contrat= contratRepository.findById(id_contrat).orElseThrow(() -> new RuntimeException("Contrat not found"));
        Retour retour = new Retour();
        retour.setContrat(contrat);
        retour.setDate_retour(LocalDate.now());
        retour.setEtat_voitureBoolean(etat);
        retourRepository.save(retour);
        if(contrat.getReservation().getStatu()=="En litige") {
            contrat.setEtat("Clos avec retard");
            contratRepository.save(contrat);
        }else {
            contrat.setEtat("Clos");
            contratRepository.save(contrat);
        }
        Reservation reservation = contrat.getReservation();
        reservation.setStatu("Termine");
        Car car =contrat.getReservation().getCar();
        car.setEtat("Disponible");
        carsRespository.save(car);
        reservationRepository.save(reservation);
        return convertDto(retour);
    }
    private RetourDto convertDto(Retour retour) {
        RetourDto retourDto=new RetourDto();
        retourDto.setId_retour(retour.getId_retour());
        retourDto.setContrat(retour.getContrat());
        retourDto.setDate_retour(retour.getDate_retour());
        retourDto.setEtat_voitureBoolean(retour.getEtat_voitureBoolean());
        return retourDto;
    }
}
