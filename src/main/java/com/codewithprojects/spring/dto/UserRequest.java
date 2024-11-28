package com.codewithprojects.spring.dto;
import com.codewithprojects.spring.enums.UserRole;
import lombok.Data;

@Data
public class UserRequest {
    private String nom;
    private String prenom;
    private String email;
    private String numero_tel;
    private String adresse;
    private UserRole role;
    private String password; // Champ pour inclure le mot de passe

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public String getEmail() {
        return email;
    }

    public String getNumero_tel() {
        return numero_tel;
    }

    public void setNumero_tel(String numero_tel) {
        this.numero_tel = numero_tel;
    }

    public String getAdresse() {
        return adresse;
    }

    public UserRole getRole() {
        return role;
    }

    public String getPassword() {
        return password;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}