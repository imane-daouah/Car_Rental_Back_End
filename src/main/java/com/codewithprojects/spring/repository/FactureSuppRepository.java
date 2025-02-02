package com.codewithprojects.spring.repository;

import com.codewithprojects.spring.entity.FactureSupplementaire;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FactureSuppRepository extends JpaRepository<FactureSupplementaire, Long> {

}
