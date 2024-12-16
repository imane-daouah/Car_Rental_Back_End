package com.codewithprojects.spring.repository;

import com.codewithprojects.spring.entity.Facture;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface FactureRepository extends JpaRepository<Facture, Long> {
	 @Query("SELECT SUM(f.montant) FROM Facture f")
	    Double sumMontants();
}
