package com.codewithprojects.spring.services.reservations;


import com.codewithprojects.spring.dto.ReservationDto;
import com.codewithprojects.spring.dto.ReservationRequest;
import com.codewithprojects.spring.entity.Car;
import com.codewithprojects.spring.entity.Contrat;
import com.codewithprojects.spring.entity.Reservation;
import com.codewithprojects.spring.entity.User;
import com.codewithprojects.spring.repository.CarsRespository;
import com.codewithprojects.spring.repository.ContratRepository;
import com.codewithprojects.spring.repository.ReservationRepository;
import com.codewithprojects.spring.repository.UserRespository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;


import java.time.*;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReservationServiceImpl implements ReservationService {
    private static final Logger logger = LoggerFactory.getLogger(ReservationServiceImpl.class);
    @Autowired
    private  ReservationRepository reservationRepository;
    @Autowired
    private  CarsRespository carsRepository;
    @Autowired
    private  UserRespository userRepository;
    @Autowired
    private ContratRepository contratRepository;

    @Override
    public ReservationDto creerReservation(ReservationRequest reservationRequest) {
        try {   // Vérifiez si la voiture et l'utilisateur existent
            Car car = carsRepository.findById((long) reservationRequest.getCarId())
                    .orElseThrow(() -> new RuntimeException("Voiture introuvable avec l'ID " + reservationRequest.getCarId()));
            User user = userRepository.findById((long) reservationRequest.getUserId())
                    .orElseThrow(() -> new RuntimeException("Utilisateur introuvable avec l'ID " + reservationRequest.getUserId()));
            // LocalDate dateDebut =convertirDateEnLocalDate(reservationRequest.getDate_debut()) ;
            //  LocalDate dateFin = convertirDateEnLocalDate(reservationRequest.getDate_fin());

            // Vérifiez si la voiture est déjà réservée avec un statut confirmé
            boolean carReservee = reservationRepository.existsByCarAndStatuAndDatesOverlap(
                    car, "Confirme", reservationRequest.getDate_debut(), reservationRequest.getDate_fin());
            if (carReservee) {
                throw new RuntimeException("La voiture est déjà réservée pour les dates sélectionnées.");
            }

            // Vérifiez si la voiture est partiellement réservée
            boolean carPartiellementReservee = reservationRepository.existsByCarAndStatuInAndDatesOverlap(
                    car, Arrays.asList("En attente", "En attente de paiement"),  reservationRequest.getDate_debut(), reservationRequest.getDate_fin());
            if (carPartiellementReservee) {
                throw new RuntimeException("La voiture est partiellement réservée pour les dates sélectionnées.");
            }
            Reservation reservation = new Reservation();
            reservation.setDate_debut(reservationRequest.getDate_debut());
            reservation.setDate_fin(reservationRequest.getDate_fin());
            reservation.setStatu("En attente");
            reservation.setCar(car);
            reservation.setDate_de_reservation(LocalDate.now());;
            reservation.setUser(user);

            Reservation reservationCree = reservationRepository.save(reservation);

            return convertirEnDto(reservationCree);
        } catch (RuntimeException e) {
            // Log l'erreur pour le debug
            System.err.println("Erreur lors de la création de la réservation : " + e.getMessage());
            throw new RuntimeException(e.getMessage()); // Relancer ou renvoyer un message clair
        }
    }

    @Override
    public ReservationDto modifierReservation(Long id, ReservationRequest reservationRequest) {
        Reservation reservationExistante = reservationRepository.findById( id)
                .orElseThrow(() -> new RuntimeException("Réservation introuvable avec l'ID " + id));



        reservationExistante.setDate_debut(reservationRequest.getDate_debut());
        reservationExistante.setDate_fin(reservationRequest.getDate_fin());



        Reservation reservationModifiee = reservationRepository.save(reservationExistante);

        return convertirEnDto(reservationModifiee);
    }

    @Override
    public void supprimerReservation(int id) {
        reservationRepository.deleteById((long) id);
    }

    @Override
    public List<ReservationDto> getToutesLesReservations() {
        return reservationRepository.findAll().stream()
                .map(this::convertirEnDto)
                .collect(Collectors.toList());
    }

    @Override
    public ReservationDto getReservationById(Long id) {
        Reservation reservation = reservationRepository.findById((long) id)
                .orElseThrow(() -> new RuntimeException("Réservation introuvable avec l'ID " + id));
        return convertirEnDto(reservation);
    }

    private ReservationDto convertirEnDto(Reservation reservation) {
        ReservationDto reservationDto = new ReservationDto();
        reservationDto.setId_reservation(reservation.getId_reservation());
        reservationDto.setDate_debut(reservation.getDate_debut());
        reservationDto.setDate_fin(reservation.getDate_fin());
        reservationDto.setStatu(reservation.getStatu());
        reservationDto.setCar(reservation.getCar());
        reservationDto.setUser(reservation.getUser());
        return reservationDto;
    }

    @Override
    public long getNombreResrvation() {

        return reservationRepository.count();
    }

    @Override
    public ReservationDto confirmerReservation(Long id) {
        Reservation reservationExistante = reservationRepository.findById((long) id)
                .orElseThrow(() -> new RuntimeException("Réservation introuvable avec l'ID " + id));



        reservationExistante.setStatu("En attente de paiement");



        Reservation reservationModifiee = reservationRepository.save(reservationExistante);

        return convertirEnDto(reservationModifiee);
    }

    @Override
    public Double montontTotal(Long id) {
        Reservation reservation = reservationRepository.findById((long) id)
                .orElseThrow(() -> new RuntimeException("Réservation introuvable avec l'ID " + id));
        //long daysBetween = ChronoUnit.DAYS.between(reservation.getDate_debut().toInstant(), reservation.getDate_fin().toInstant());
        LocalDate dateDebut = reservation.getDate_debut().toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate();

        LocalDate dateFin = reservation.getDate_fin().toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate();

// Calcul de la différence en jours
        long daysBetween = Period.between(dateDebut, dateFin).getDays();
        Double montontTotalDouble=   daysBetween * reservation.getCar().getTarif();
        return montontTotalDouble;
    }

    @Override
    public long getNombreResrvationDuClient(Long id) {
        return reservationRepository.countByClientId(id);
    }

    @Override
    public long getNombreResrvationDuClientAndStatu(Long id,String statu) {

        return reservationRepository.countByClientIdAndStatu(id, statu);
    }

    @Override
    @Scheduled(cron = "0 0 0 * * ?")
    public void mettreAJourStatutReservations() {
        logger.info("Mise à jour des statuts des réservations démarrée.");
        List<Reservation> reservations = reservationRepository.findAll();

        for (Reservation reservation : reservations) {
            LocalDate dateDeDebut = convertirDateEnLocalDate(reservation.getDate_debut());
            LocalDate dateDeFin = convertirDateEnLocalDate(reservation.getDate_fin());
            // Vérifie si le statut est "En attente" ou "En attente de paiement" et que la date de réservation dépasse 2 jours
            if (reservation.getStatu().equalsIgnoreCase("En attente") && reservation.getDate_de_reservation().isBefore(LocalDate.now().minusDays(2)) ){

                reservation.setStatu("Annule");
                Car car= reservation.getCar();
                car.setEtat("Disponible");
                this.carsRepository.save(car); // Remettre la voiture en état disponible si nécessaire

            } else if (reservation.getStatu().equalsIgnoreCase("En attente de paiement") && reservation.getDate_de_reservation().isBefore(LocalDate.now().minusDays(2))) {
                reservation.setStatu("Annulee");
                Car car= reservation.getCar();
                car.setEtat("Disponible");
                this.carsRepository.save(car);
                Contrat contrat=  this.contratRepository.contratByreservationId(reservation.getId_reservation());
                contrat.setEtat("Annulee");
                this.contratRepository.save(contrat);
            }
            // Vérifie si le statut est "Confirmé" et que la date actuelle est entre date début et date fin
            else if (reservation.getStatu().equalsIgnoreCase("Confirme")
                    && !LocalDate.now().isBefore(dateDeDebut)
                    && !LocalDate.now().isAfter(dateDeFin)){

                reservation.setStatu("En cours");
                Car car= reservation.getCar();
                car.setEtat("Reserve");
                this.carsRepository.save(car); // Mettre à jour l'état de la voiture
            }else if (reservation.getStatu().equalsIgnoreCase("En cours"))
            {
                //long daysBetween = ChronoUnit.DAYS.between(dateDeFin, LocalDate.now());
                LocalDateTime dateDeFinTime = dateDeFin.atStartOfDay();
                LocalDateTime dateNow = LocalDate.now().atStartOfDay();

// Calcul de la différence en jours
                long daysBetween = Duration.between(dateDeFinTime, dateNow).toDays();
                if(daysBetween>1) {
                    reservation.setStatu("En litige");
                    Car car= reservation.getCar();
                    car.setEtat("Reserve");
                    this.carsRepository.save(car); // Mettre à jour l'état de la voiture
                }
            }
            // Sauvegarde les modifications
            reservationRepository.save(reservation);
        }
        logger.info("Mise à jour des statuts des réservations terminée.");
    }

    private LocalDate convertirDateEnLocalDate(Date date) {
        return date.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate();
    }

    @Override
    public List<ReservationDto> getToutesLesReservationsDuClient(Long id_client) {

        return reservationRepository.reservationClientId(id_client).stream().map(this::convertirEnDto)
                .collect(Collectors.toList());
    }

    @Override
    public void annulerReservation(Long id) {
        Reservation reservationExistante = reservationRepository.findById((long) id)
                .orElseThrow(() -> new RuntimeException("Réservation introuvable avec l'ID " + id));


        if(reservationExistante.getStatu().equals("En attente")) {
            reservationExistante.setStatu("Annulee");
            reservationRepository.save(reservationExistante);
        }else if (reservationExistante.getStatu().equals("En attente de paiement")) {
            reservationExistante.setStatu("Annulee");
            reservationRepository.save(reservationExistante);
            Contrat contrat=  this.contratRepository.contratByreservationId(reservationExistante.getId_reservation());
            contrat.setEtat("Annulee");
            this.contratRepository.save(contrat);
        }

        else {
            reservationExistante.setStatu("Annulee");
            reservationRepository.save(reservationExistante);
            Contrat contrat=  this.contratRepository.contratByreservationId(reservationExistante.getId_reservation());
            contrat.setEtat("Annulee");
            this.contratRepository.save(contrat);
        }

    }

	
}
