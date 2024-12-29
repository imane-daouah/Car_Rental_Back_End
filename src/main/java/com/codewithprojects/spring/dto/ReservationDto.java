package com.codewithprojects.spring.dto;
import com.codewithprojects.spring.entity.Car;
import com.codewithprojects.spring.entity.User;
import lombok.Data;

import java.util.Date;
@Data
public class ReservationDto {
    private Long id_reservation;
    private Date date_debut;
    private Date date_fin;
    private String statu;
    private Car car;    // Objet de la voiture
    private User user;  // Objet de l'utilisateur
	
    



}