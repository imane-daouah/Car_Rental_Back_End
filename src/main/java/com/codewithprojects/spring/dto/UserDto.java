package com.codewithprojects.spring.dto;

import lombok.Data;
@Data
public class UserDto {
	
	private Long id;
	private String nom;
	private String prenom;
	private String email;
	private String numero_tel;
	private String adresse;
    private String password;
    
    private String role;

	

}
