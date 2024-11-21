package com.codewithprojects.spring.dto;

import com.codewithprojects.spring.enums.UserRole;
import lombok.Data;

@Data
public class SignupRequest {


    private String email;
    private String nom;
    private String password;
    private String prenom;
    private String numero_tel;

    private String adresse ;
    private UserRole userRole;

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getNom() {
        return nom;
    }
    public void setNom(String nom) {
        this.nom = nom;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    public String getPrenom() {
        return prenom;
    }

    public String getNumero_tel() {
        return numero_tel;
    }

    public String getAdresse() {
        return adresse;
    }



    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public void setNumero_tel(String numero_tel) {
        this.numero_tel = numero_tel;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }


}
