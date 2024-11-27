package com.codewithprojects.spring.controller;

import com.codewithprojects.spring.dto.CarsDto;
import com.codewithprojects.spring.dto.CarsRequest;
import com.codewithprojects.spring.services.cars.CarsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/cars")
@RequiredArgsConstructor
public class CarsController {

    private final CarsService carsService; // Lombok injectera automatiquement la dépendance.

    @PostMapping("/creer")
    public ResponseEntity<CarsDto> createCar(@RequestBody CarsRequest carsRequest) {
        CarsDto createdCar = carsService.creerCars(carsRequest); // Utilise le service pour créer une voiture.
        return ResponseEntity.ok(createdCar); // Renvoie une réponse HTTP 200 avec le DTO de la voiture créée.
    }
}
