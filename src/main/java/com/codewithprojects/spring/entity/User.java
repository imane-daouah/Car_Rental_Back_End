package com.codewithprojects.spring.entity;



import jakarta.persistence.*;



@Entity
@DiscriminatorValue("Client")
public class User extends Personne{
	
	/*@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	
	private String nom;
	
	
	private String prenom;
	
	
	private String email;
	
	private String adresse;
	
	private String numero_tel;
	
	
	private UserRole userRole;
	
	
	private String password;


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getNom() {
		return nom;
	}


	public void setNom(String nom) {
		this.nom = nom;
	}


	public String getPrenom() {
		return prenom;
	}


	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getNumero_tel() {
		return numero_tel;
	}


	public void setNumero_tel(String numero_tel) {
		this.numero_tel = numero_tel;
	}


	public UserRole getUserRole() {
		return userRole;
	}


	public void setUserRole(UserRole userRole) {
		this.userRole = userRole;
	}


	public String getAdresse() {
		return adresse;
	}


	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public User() {
		super();
	}
	*/
	

}
