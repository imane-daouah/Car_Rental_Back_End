package com.codewithprojects.spring.entity;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
@Entity
@Data
@Table(name ="reservations")

public class Reservation {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id_reservation ;
    private Date date_debut;
    private Date date_fin ;
    private String status ;
    @ManyToOne
    @JoinColumn(name = "id_car", nullable = false)
    private Car car;

    @ManyToOne
    @JoinColumn(name = "id_user", nullable = false)
    private User user;

    public void setId_reservation(int id_reservation) {
        this.id_reservation = id_reservation;
    }

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

    public int getId_reservation() {
        return id_reservation;
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
}
