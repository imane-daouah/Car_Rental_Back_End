package com.codewithprojects.spring.controller;

import com.codewithprojects.spring.dto.AdminDto;
import com.codewithprojects.spring.dto.LoginRequest;
import com.codewithprojects.spring.dto.SignupRequest;
import com.codewithprojects.spring.dto.UserDto;
import com.codewithprojects.spring.services.auth.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import lombok.RequiredArgsConstructor;
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor

public class AuthController {
	@Autowired
	private  AuthService authService;

	@PostMapping("/signup")
	public ResponseEntity<?> signupCustomer(@RequestBody SignupRequest signupRequest){
		if(authService.hasCustomerWithEmail(signupRequest.getEmail())) {
			return new ResponseEntity<>("Customer already exit this email",HttpStatus.NOT_ACCEPTABLE);
		}
		UserDto createdCustomerDto = authService.createCustomer(signupRequest);
		if(createdCustomerDto==null)return new ResponseEntity<>
				("Customer not created, come again later ",HttpStatus.CREATED);

		return new ResponseEntity<>(createdCustomerDto,HttpStatus.CREATED);

	}

	@PostMapping("/signup/admin")
	public ResponseEntity<?> signupAdmin(@RequestBody SignupRequest signupRequest){
		if(authService.hasCustomerWithEmail(signupRequest.getEmail())) {
			return new ResponseEntity<>("Admin already exit this email",HttpStatus.NOT_ACCEPTABLE);
		}
		AdminDto createdCustomerDto = authService.createAdmin(signupRequest);
		if(createdCustomerDto==null)return new ResponseEntity<>
				("Admin not created, come again later ",HttpStatus.CREATED);

		return new ResponseEntity<>(createdCustomerDto,HttpStatus.CREATED);

	}

	@PostMapping("/login")
	public ResponseEntity<?> loginCustomer(@RequestBody LoginRequest loginRequest) {
		try {
			UserDto loggedInCustomer = authService.loginCustomer(loginRequest);
			return ResponseEntity.ok(loggedInCustomer);
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.UNAUTHORIZED);
		}
	}

	@PostMapping("/login1")
	public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
		try {
			UserDto personneDto = authService.login(loginRequest) ;
			return ResponseEntity.ok(personneDto);
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.UNAUTHORIZED);
		}
	}
}

