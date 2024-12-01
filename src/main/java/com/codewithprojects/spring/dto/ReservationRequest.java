package com.codewithprojects.spring.dto;

import lombok.Data;

import java.util.Date;

@Data
public class ReservationRequest {
    private Date date_debut;
    private Date date_fin;
    private String status;
    private int carId;  // ID de la voiture
    private int userId;// ID de l'utilisateur

    public void setDate_debut(Date date_debut) {
        this.date_debut = date_debut;
    }

    public void setDate_fin(Date date_fin) {
        this.date_fin = date_fin;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setCarId(int carId) {
        this.carId = carId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public Date getDate_debut() {
        return date_debut;
    }

    public Date getDate_fin() {
        return date_fin;
    }

    public String getStatus() {
        return status;
    }

    public int getCarId() {
        return carId;
    }

    public int getUserId() {
        return userId;
    }
}