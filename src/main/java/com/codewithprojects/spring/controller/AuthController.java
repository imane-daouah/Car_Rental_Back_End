package com.codewithprojects.spring.controller;

import com.codewithprojects.spring.dto.SignupRequest;
import com.codewithprojects.spring.dto.UserDto;
import com.codewithprojects.spring.services.auth.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import lombok.RequiredArgsConstructor;
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor

public class AuthController {
    @Autowired
    private final AuthService authService;

    @PostMapping("/signup")
    public ResponseEntity<?> signupCustomer(@RequestBody SignupRequest signupRequest){
        if (authService.hasCustomerWithEmail(signupRequest.getEmail())) {
            return new ResponseEntity<>(
                    "Customer already exists with this email",
                    HttpStatus.NOT_ACCEPTABLE
            );
        }

        UserDto createdCustomerDto = authService.createCustomer(signupRequest);
        if(createdCustomerDto==null)return new ResponseEntity<>
                ("Customer not created, come again later ",HttpStatus.CREATED);

        return new ResponseEntity<>(createdCustomerDto,HttpStatus.CREATED);

    }


}
