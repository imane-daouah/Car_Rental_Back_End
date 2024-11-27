package com.codewithprojects.spring.services.cars;

import com.codewithprojects.spring.dto.CarsDto;
import com.codewithprojects.spring.dto.CarsRequest;
import com.codewithprojects.spring.entity.Car;

import com.codewithprojects.spring.repository.CarsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CarsServiceImpl implements CarsService {

    @Autowired
    private CarsRepository carsRepository;

    @Override
    public CarsDto creerCars(CarsRequest carsRequest) {
        // Vérifiez que l'objet carsRequest n'est pas null
        if (carsRequest == null) {
            throw new IllegalArgumentException("La requête carsRequest ne peut pas être null.");
        }

        // Créer une nouvelle instance de l'entité Car
        Car car = new Car();
        car.setAnnee(carsRequest.getAnnee());
        car.setEtat(carsRequest.getEtat()); // Correction : récupérer `etat` depuis la requête
        car.setMarque(carsRequest.getMarque());
        car.setType(carsRequest.getType());
        car.setTarif(carsRequest.getTarif());
        car.setModele(carsRequest.getModele());

        // Enregistrer l'entité dans le repository
        Car creerCar = carsRepository.save(car);

        // Créer et retourner le DTO
        CarsDto carsDto = new CarsDto();
        carsDto.setId(creerCar.getId());
        return carsDto;
    }
}


