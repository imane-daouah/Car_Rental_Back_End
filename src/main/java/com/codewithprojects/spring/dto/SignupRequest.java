package com.codewithprojects.spring.dto;

import lombok.Data;

@Data
public class SignupRequest {

	private String prenom;
	private String email;
	private String nom;
	private String password;
	private String numero_tel;
	private String adresse;

	
	
}
