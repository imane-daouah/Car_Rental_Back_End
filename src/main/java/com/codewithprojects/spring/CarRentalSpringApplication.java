package com.codewithprojects.spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication

public class CarRentalSpringApplication {

	public static void main(String[] args) {
		SpringApplication.run(CarRentalSpringApplication.class, args);
	}

}
