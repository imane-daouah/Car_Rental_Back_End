package com.codewithprojects.spring.services.admin;

import com.codewithprojects.spring.dto.AdminDto;
import com.codewithprojects.spring.dto.UserRequest;
import org.springframework.stereotype.Service;



@Service
public interface AdminService {
    AdminDto modifierMotDePasseAvecVerification(Long id, String ancienMotDePasse, String nouveauMotDePasse);
    AdminDto modifierUtilisateur(Long id, UserRequest userRequest);
}
