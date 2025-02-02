package com.codewithprojects.spring.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data

public class UserRequest {

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

}
