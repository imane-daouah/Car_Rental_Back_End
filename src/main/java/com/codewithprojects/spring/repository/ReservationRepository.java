package com.codewithprojects.spring.repository;

import com.codewithprojects.spring.entity.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
public interface ReservationRepository extends JpaRepository<Reservation, Long> {

}
