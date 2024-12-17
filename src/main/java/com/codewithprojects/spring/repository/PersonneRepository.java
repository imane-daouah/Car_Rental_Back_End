package com.codewithprojects.spring.repository;

import com.codewithprojects.spring.entity.Personne;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
public interface PersonneRepository extends JpaRepository<Personne, Long> {
	 Optional<Personne> findByEmail(String email);
}
