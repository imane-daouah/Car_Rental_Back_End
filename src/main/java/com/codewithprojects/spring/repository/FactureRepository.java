package com.codewithprojects.spring.repository;


import com.codewithprojects.spring.entity.Facture;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FactureRepository extends JpaRepository<Facture, Long> {
}
