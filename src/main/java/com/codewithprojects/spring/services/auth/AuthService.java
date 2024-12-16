package com.codewithprojects.spring.services.auth;

import com.codewithprojects.spring.dto.AdminDto;
import com.codewithprojects.spring.dto.LoginRequest;
import com.codewithprojects.spring.dto.SignupRequest;
import com.codewithprojects.spring.dto.UserDto;
import org.springframework.stereotype.Service;


@Service
public interface AuthService {

	 UserDto createCustomer(SignupRequest signupRequest);
	 AdminDto createAdmin(SignupRequest signupRequest);
	 
	 boolean hasCustomerWithEmail(String email);
	 
	 UserDto loginCustomer(LoginRequest loginRequest);
	 
	 UserDto login(LoginRequest loginRequest);
}

