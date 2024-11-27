package com.codewithprojects.spring.services.cars;

import com.codewithprojects.spring.dto.CarsDto;
import com.codewithprojects.spring.dto.CarsRequest;
import org.springframework.stereotype.Service;

@Service
public interface CarsService {
    CarsDto creerCars(CarsRequest carsRequest);


}
