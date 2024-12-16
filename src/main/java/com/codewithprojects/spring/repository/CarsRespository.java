package com.codewithprojects.spring.repository;

import com.codewithprojects.spring.entity.Car;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CarsRespository extends JpaRepository<Car, Long> {

}
