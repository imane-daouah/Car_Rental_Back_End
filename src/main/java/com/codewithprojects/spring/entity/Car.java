package com.codewithprojects.spring.entity;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@Table(name="cars")
public class Car {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	private String marque;
	
	private Integer modele;
	
	private Integer annee;
	
	private String type;
	
	private Double tarif;
	
	private String etat;
	
	private String image;
	
	private String description;

	
}
