package com.codewithprojects.spring.services.cars;

import java.util.List;
import java.util.stream.Collectors;

import com.codewithprojects.spring.dto.CarsDto;
import com.codewithprojects.spring.dto.CarsRequest;
import com.codewithprojects.spring.entity.Car;
import com.codewithprojects.spring.repository.CarsRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CarsServiceImpl implements CarsService {
	@Autowired
	private CarsRespository carsRepository;
	@Autowired
	private CarsRespository carsRespository;

	@Override
	public List<CarsDto> getCarsByEtat(String etat) {
		return carsRepository.findByEtat(etat).stream()
				.map(CarsDto::fromEntity) // Convertir chaque entité en DTO
				.collect(Collectors.toList());
	}

	@Override
	public CarsDto creerCars(CarsRequest carsRequest) {
		Car car=new Car();
		car.setMarque(carsRequest.getMarque());
		car.setModele(carsRequest.getModele());
		car.setAnnee(carsRequest.getAnnee());
		car.setEtat(carsRequest.getEtat());
		car.setTarif(carsRequest.getTarif());
		car.setType(carsRequest.getType());
		car.setImage(carsRequest.getImage());
		car.setDescription(carsRequest.getDescription());
		Car creerCar = carsRepository.save(car);
		CarsDto carDto = new CarsDto();
		carDto.setId(creerCar.getId());
		return carDto;
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
		// car.setImage(carsRequest.getImage());
		car.setDescription(carsRequest.getDescription());
		Car updatedCar = carsRepository.save(car);


		return CarsDto.fromEntity(updatedCar);
	}

	@Override
	public void supprimerCars(Long id) {
		carsRepository.deleteById(id);
	}

	@Override
	public List<CarsDto> getTousCars() {
		return carsRepository.findAll().stream().map(car -> {

			return CarsDto.fromEntity(car);
		}).collect(Collectors.toList());
	}
	@Override
	public CarsDto getCarsById(Long id) {
		Car car = carsRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Voiture introuvable avec l'ID : " + id));
		return CarsDto.fromEntity(car);
	}

}
