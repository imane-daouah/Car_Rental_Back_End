package com.codewithprojects.spring.entity;


import com.codewithprojects.spring.enums.UserRole;
import jakarta.persistence.Entity;
import jakarta.persistence.*;

import lombok.Data;

@Entity
@Data
@Table(name="users")
public class User {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;


    private String nom;


    private String prenom;


    private String email;


    private int numero_tel;


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


    public int getNumero_tel() {
        return numero_tel;
    }


    public void setNumero_tel(int numero_tel) {
        this.numero_tel = numero_tel;
    }


    public UserRole getUserRole() {
        return userRole;
    }


    public void setUserRole(UserRole userRole) {
        this.userRole = userRole;
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



}
