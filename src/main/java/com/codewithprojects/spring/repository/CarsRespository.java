package com.codewithprojects.spring.repository;

import com.codewithprojects.spring.entity.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface CarsRespository extends JpaRepository<Car, Long> {
 /*	@Query("SELECT c FROM Car c WHERE " +
            "(:type IS NULL OR c.type = :type) AND " +
            "(:marque IS NULL OR c.marque LIKE %:marque%) AND " +
            "(:tarif IS NULL OR c.tarif = :tarif)")*/

    @Query("SELECT c FROM Car c WHERE " +
            "(:type IS not NULL and c.type = :type) or " +
            "(:marque IS not NULL and c.marque LIKE %:marque%) or " +
            "(:tarif IS not NULL and c.tarif = :tarif) " )
    List<Car> findCarsByFilters(
            @Param("type") String type,
            @Param("marque") String marque,
            @Param("tarif") Double tarif
    );


}
