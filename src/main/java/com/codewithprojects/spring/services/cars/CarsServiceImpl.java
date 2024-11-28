package com.codewithprojects.spring.services.cars;

import com.codewithprojects.spring.dto.CarsDto;
import com.codewithprojects.spring.dto.CarsRequest;
import com.codewithprojects.spring.entity.Car;

import com.codewithprojects.spring.repository.CarsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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

    @Override
    public CarsDto modifierCars(Long id, CarsRequest carsRequest) {
        Car car = carsRepository.findById(id).orElseThrow(() -> new RuntimeException("Voiture introuvable"));
        car.setMarque(carsRequest.getMarque());
        car.setModele(carsRequest.getModele());
        car.setAnnee(carsRequest.getAnnee());
        car.setType(carsRequest.getType());
        car.setTarif(carsRequest.getTarif());
        car.setEtat(carsRequest.getEtat());

        Car updatedCar = carsRepository.save(car);

        CarsDto carsDto = new CarsDto();
        carsDto.setId(updatedCar.getId());
        carsDto.setMarque(updatedCar.getMarque());
        carsDto.setModele(updatedCar.getModele());
        carsDto.setAnnee(updatedCar.getAnnee());
        carsDto.setType(updatedCar.getType());
        carsDto.setTarif(updatedCar.getTarif());
        carsDto.setEtat(updatedCar.getEtat());

        return carsDto;
    }

    @Override
    public void supprimerCars(Long id) {
        carsRepository.deleteById(id);
    }

    @Override
    public List<CarsDto> getTousCars() {
        return carsRepository.findAll().stream().map(car -> {
            CarsDto carsDto = new CarsDto();
            carsDto.setId(car.getId());
            carsDto.setMarque(car.getMarque());
            carsDto.setModele(car.getModele());
            carsDto.setAnnee(car.getAnnee());
            carsDto.setType(car.getType());
            carsDto.setTarif(car.getTarif());
            carsDto.setEtat(car.getEtat());
            return carsDto;
        }).collect(Collectors.toList());
    }
}


