package com.codewithprojects.spring.dto;

import lombok.Data;

@Data
public class LoginRequest {
    private String email;
    private String password;

    // Getters et setters

}