package com.codewithprojects.spring.repository;

import com.codewithprojects.spring.entity.Car;
import org.hibernate.annotations.Parameter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface CarsRespository extends JpaRepository<Car, Long> {
    @Query("select c from Car c where c.etat = :etat")
    List<Car> findByEtat(@Param("etat") String etat);


}
