package com.codewithprojects.spring.services.auth;

import com.codewithprojects.spring.dto.SignupRequest;
import com.codewithprojects.spring.dto.UserDto;
import com.codewithprojects.spring.entity.User;
import com.codewithprojects.spring.enums.UserRole;
import com.codewithprojects.spring.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder; // Injection via Lombok (@RequiredArgsConstructor)

    @Override
    public UserDto createCustomer(SignupRequest signupRequest) {
        // Création d'un nouvel utilisateur
        User user = new User();
        user.setNom(signupRequest.getNom());
        user.setEmail(signupRequest.getEmail());
        user.setPassword(passwordEncoder.encode(signupRequest.getPassword())); // Utilisation correcte
        user.setPrenom(signupRequest.getPrenom());
        user.setAdresse(signupRequest.getAdresse());
        user.setNumero_tel(signupRequest.getNumero_tel());
        user.setUserRole(UserRole.CUSTOMER);

        // Sauvegarder l'utilisateur dans la base de données
        User creerUser = userRepository.save(user);

        // Créer un DTO pour retourner les données au client
        UserDto userDto = new UserDto();
        userDto.setId(creerUser.getId());
        return userDto;
    }

    @Override
    public boolean hasCustomerWithEmail(String email) {
        return userRepository.findFirstByEmail(email).isPresent();
    }
}
