package com.codewithprojects.spring.dto;



import lombok.Data;

import java.util.Date;

@Data
public class ReservationRequest {
    private Date date_debut;
    private Date date_fin;
  
    private Long carId;  // ID de la voiture
    private Long userId; // ID de l'utilisateur
    
    
	public Date getDate_debut() {
		return date_debut;
	}
	public void setDate_debut(Date date_debut) {
		this.date_debut = date_debut;
	}
	public Date getDate_fin() {
		return date_fin;
	}
	public void setDate_fin(Date date_fin) {
		this.date_fin = date_fin;
	}
	
	public Long getCarId() {
		return carId;
	}
	public void setCarId(Long carId) {
		this.carId = carId;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	
    
    
}