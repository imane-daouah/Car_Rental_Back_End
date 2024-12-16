package com.codewithprojects.spring.controller;

import java.util.List;
import com.codewithprojects.spring.dto.CarsDto;
import com.codewithprojects.spring.dto.CarsRequest;
import com.codewithprojects.spring.services.cars.CarsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import lombok.RequiredArgsConstructor;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/cars")
@RequiredArgsConstructor
public class CarsController {
	@Autowired
	private CarsService carsService;
	
	
	@PostMapping("/creer")
	 public ResponseEntity<CarsDto> createCar(@RequestBody CarsRequest carsRequest) {
        CarsDto createdCar = carsService.creerCars(carsRequest);
        return ResponseEntity.ok(createdCar);
    }
	
	// Modifier une voiture existante
    @PutMapping("/modifier/{id}")
    public ResponseEntity<CarsDto> updateCar(@PathVariable Long id, @RequestBody CarsRequest carsRequest) {
        CarsDto updatedCar = carsService.modifierCars(id, carsRequest);
        return ResponseEntity.ok(updatedCar);
    }

    // Supprimer une voiture par ID
    @DeleteMapping("/supprimer/{id}")
    public ResponseEntity<Void> deleteCar(@PathVariable Long id) {
        carsService.supprimerCars(id);
        return ResponseEntity.noContent().build();
    }

    // Afficher toutes les voitures
    @GetMapping("/tous")
    public ResponseEntity<List<CarsDto>> getAllCars() {
        List<CarsDto> carsList = carsService.getTousCars();
        return ResponseEntity.ok(carsList);
    }
    @GetMapping("detail/{id}")
    public CarsDto getCarById(@PathVariable Long id) {
        return carsService.getCarsById(id);
    }
}
