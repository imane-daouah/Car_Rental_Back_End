package com.codewithprojects.spring.dto;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserRequest {
private Long id;
	
	
	private String nom;
	private String prenom;
	private String email;
	
	private String adresse;
	
	private String numero_tel;
	private String password;
	
}
