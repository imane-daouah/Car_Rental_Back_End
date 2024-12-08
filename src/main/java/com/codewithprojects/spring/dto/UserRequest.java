package com.codewithprojects.spring.dto;
import lombok.Data;
@Data
public class UserRequest {
    private Long id;


    private String nom;


    private String prenom;


    private String email;

    private String adresse;

    private String numero_tel;



    private String password;


    public UserRequest() {
        super();
        // TODO Auto-generated constructor stub
    }


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


    public String getAdresse() {
        return adresse;
    }


    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }


    public String getNumero_tel() {
        return numero_tel;
    }


    public void setNumero_tel(String numero_tel) {
        this.numero_tel = numero_tel;
    }




    public String getPassword() {
        return password;
    }


    public void setPassword(String password) {
        this.password = password;
    }


}