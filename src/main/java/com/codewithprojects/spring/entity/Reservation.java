package com.codewithprojects.spring.entity;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name="reservations")
public class Reservation {
    
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id_resrvation;
	@ManyToOne
	@JoinColumn(name = "id_car",nullable = false)
	private Car car;
	@ManyToOne
	@JoinColumn(name = "id_user",nullable = false)
	private User user;
	private Date date_debut;
	private Date date_fin;
	private String statu;
	public Reservation() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Long getId_resrvation() {
		return id_resrvation;
	}

	public void setId_resrvation(Long id_resrvation) {
		this.id_resrvation = id_resrvation;
	}

	public Date getDate_debut() {
		return date_debut;
	}
	public void setDate_debut(Date date) {
		this.date_debut = date;
	}
	public Date getDate_fin() {
		return date_fin;
	}
	public void setDate_fin(Date date_fin) {
		this.date_fin = date_fin;
	}
	public String getStatu() {
		return statu;
	}
	public void setStatu(String statu) {
		this.statu = statu;
	}
	public Car getCar() {
		return car;
	}
	public void setCar(Car car) {
		this.car = car;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
		
	
}
