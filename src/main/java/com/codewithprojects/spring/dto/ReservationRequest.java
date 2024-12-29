package com.codewithprojects.spring.dto;



import lombok.Data;

import java.util.Date;

@Data
public class ReservationRequest {
    private Date date_debut;
    private Date date_fin;
  
    private Long carId;  // ID de la voiture
    private Long userId; // ID de l'utilisateur
    
    

    
    
}