package com.codewithprojects.spring.services.auth;

import com.codewithprojects.spring.dto.SignupRequest;
import com.codewithprojects.spring.dto.UserDto;
import org.springframework.stereotype.Service;

@Service
public interface AuthService {
    boolean hasCustomerWithEmail(String email);
    UserDto createCustomer(SignupRequest signupRequest);
}
