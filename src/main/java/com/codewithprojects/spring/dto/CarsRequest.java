package com.codewithprojects.spring.dto;

import lombok.Data;

@Data
public class CarsRequest {
private String marque;
	
	private Integer modele;
	
	private Integer annee;
	
	private String type;
	
	private Double tarif;
	
	private String etat;
	
	private String image;

	private String description;

}
