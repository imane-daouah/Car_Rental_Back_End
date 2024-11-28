package com.codewithprojects.spring.services.user;


import com.codewithprojects.spring.dto.UserDto;
import com.codewithprojects.spring.dto.UserRequest;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface UserService {
    UserDto creerUtilisateur(UserRequest userRequest);
    UserDto modifierUtilisateur(Long id, UserRequest userRequest);
    void supprimerUtilisateur(Long id);
    List<UserDto> getTousUtilisateurs();
    UserDto modifierMotDePasse(Long id, String nouveauMotDePasse);
    UserDto creerUtilisateurSiInexistant(UserRequest userRequest);
}