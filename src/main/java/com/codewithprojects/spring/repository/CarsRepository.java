package com.codewithprojects.spring.repository;

import com.codewithprojects.spring.entity.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CarsRepository extends JpaRepository<Car, Long> {
}
