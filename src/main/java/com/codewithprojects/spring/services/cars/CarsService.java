package com.codewithprojects.spring.services.cars;

import java.util.List;

import com.codewithprojects.spring.dto.CarsDto;
import com.codewithprojects.spring.dto.CarsRequest;
import com.codewithprojects.spring.entity.Car;
import org.springframework.stereotype.Service;
@Service
public interface CarsService {
	// Méthode pour créer une voiture
	CarsDto creerCars(CarsRequest carsRequest);

	CarsDto modifierCars(Long id, CarsRequest carsRequest);

	void supprimerCars(Long id);
	Long getNombreCars();
	List<CarsDto> getTousCars();

	// Méthode pour récupérer une voiture par ID
	CarsDto getCarsById(Long id);

	List<CarsDto> searchCars(String type, String marque, Double tarif);


}
