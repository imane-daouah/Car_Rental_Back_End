package com.codewithprojects.spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories("com.codewithprojects.spring.repository") // Spécifiez où se trouvent vos repositories
public class CarRentalSpringApplication {

	public static void main(String[] args) {
		SpringApplication.run(CarRentalSpringApplication.class, args);
	}

}
