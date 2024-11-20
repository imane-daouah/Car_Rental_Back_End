package com.codewithprojects.spring.services.auth;

import com.codewithprojects.spring.dto.SignupRequest;
import com.codewithprojects.spring.dto.UserDto;
import com.codewithprojects.spring.entity.User;
import com.codewithprojects.spring.enums.UserRole;
import com.codewithprojects.spring.repository.UserRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService{
    @Autowired
    private  final UserRespository userReepository;

    @Override
    public UserDto createCustomer(SignupRequest signupRequest) {
        User user=new User();
        user.setNom(signupRequest.getNom());
        user.setEmail(signupRequest.getEmail());
        user.setPassword(signupRequest.getPassword());
        user.setUserRole(UserRole.CUSTOMER);
        User creerUser = userReepository.save(user);
        UserDto userDto=new UserDto();
        userDto.setId(creerUser.getId());
        return userDto;
    }



}
