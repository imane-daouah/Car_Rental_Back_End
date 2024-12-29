package com.codewithprojects.spring.dto;

import com.codewithprojects.spring.entity.Car;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor // cree un constructeur sans arrgument
public class CarsDto {
private Long id;
	
	private String marque;
	
	private Integer modele;
	
	private Integer annee;
	
	private String type;
	
	private Double tarif;
	
	private String etat;
	
	private String image;

	private String description;


	public static CarsDto fromEntity(Car car) {
		CarsDto carsDto = new CarsDto();
        carsDto.setId(car.getId());
        carsDto.setMarque(car.getMarque());
        carsDto.setModele(car.getModele());
        carsDto.setAnnee(car.getAnnee());
        carsDto.setType(car.getType());
        carsDto.setTarif(car.getTarif());
        carsDto.setEtat(car.getEtat());
        carsDto.setImage(car.getImage());
        carsDto.setDescription(car.getDescription());
		return carsDto;
	}
	
}
