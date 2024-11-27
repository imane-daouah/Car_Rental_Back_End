package com.codewithprojects.spring.repository;

import com.codewithprojects.spring.entity.Car;
import com.codewithprojects.spring.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CarsRepository extends JpaRepository<Car, Long> {
}
