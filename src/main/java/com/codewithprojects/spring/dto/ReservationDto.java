package com.codewithprojects.spring.dto;

import com.codewithprojects.spring.entity.Car;
import com.codewithprojects.spring.entity.User;
import lombok.Data;

import java.util.Date;

@Data
public class ReservationDto {
    private long id_reservation;
    private Date date_debut;
    private Date date_fin;
    private String status;
    private Car car;    // Objet de la voiture
    private User user;  // Objet de l'utilisateur


    public void setDate_debut(Date date_debut) {
        this.date_debut = date_debut;
    }

    public void setDate_fin(Date date_fin) {
        this.date_fin = date_fin;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public void setUser(User user) {
        this.user = user;
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

    public Car getCar() {
        return car;
    }

    public User getUser() {
        return user;
    }

    public long getId_reservation() {
        return id_reservation;
    }

    public void setId_reservation(long id_reservation) {
        this.id_reservation = id_reservation;
    }
}
