package com.codewithprojects.spring.entity;

import java.util.Date;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name="reservations")
public class Reservation {
    
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "id_reservation") // Corrigez l'orthographe si nécessaire

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
	

		
	
}
