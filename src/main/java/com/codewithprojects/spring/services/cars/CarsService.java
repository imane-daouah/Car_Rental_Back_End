package com.codewithprojects.spring.services.cars;

import com.codewithprojects.spring.dto.CarsDto;
import com.codewithprojects.spring.dto.CarsRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CarsService {
    CarsDto creerCars(CarsRequest carsRequest);
    CarsDto modifierCars(Long id, CarsRequest carsRequest);
    void supprimerCars(Long id);
    List<CarsDto> getTousCars();


}
