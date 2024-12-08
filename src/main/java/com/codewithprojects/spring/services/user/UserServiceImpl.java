package com.codewithprojects.spring.services.user;


import com.codewithprojects.spring.dto.UserDto;
import com.codewithprojects.spring.dto.UserRequest;
import com.codewithprojects.spring.entity.User;
import com.codewithprojects.spring.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    @Autowired
    private final UserRepository userRepository;

    @Override
    public UserDto creerUtilisateur(UserRequest userRequest) {
        User user = new User();
        user.setNom(userRequest.getNom());
        user.setPrenom(userRequest.getPrenom());
        user.setEmail(userRequest.getEmail());
        user.setNumero_tel(userRequest.getNumero_tel());
        user.setAdresse(userRequest.getAdresse());
        user.setPassword(userRequest.getPassword()); // Enregistrer le mot de passe

        User utilisateurCree = userRepository.save(user);

        return convertirEnDto(utilisateurCree);
    }

    @Override
    public UserDto modifierUtilisateur(Long id, UserRequest userRequest) {
        User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("Utilisateur introuvable"));

        user.setNom(userRequest.getNom());
        user.setPrenom(userRequest.getPrenom());
        user.setEmail(userRequest.getEmail());
        user.setNumero_tel(userRequest.getNumero_tel());
        user.setAdresse(userRequest.getAdresse());

        User utilisateurModifie = userRepository.save(user);

        return convertirEnDto(utilisateurModifie);
    }

    @Override
    public void supprimerUtilisateur(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public List<UserDto> getTousUtilisateurs() {
        return userRepository.findAll().stream()
                .map(this::convertirEnDto)
                .collect(Collectors.toList());
    }

    @Override
    public UserDto modifierMotDePasse(Long id, String nouveauMotDePasse) {
        User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("Utilisateur introuvable"));

        if (nouveauMotDePasse == null || nouveauMotDePasse.isEmpty()) {
            throw new IllegalArgumentException("Le mot de passe ne peut pas être vide.");
        }

        user.setPassword(nouveauMotDePasse); // Mise à jour du mot de passe
        User utilisateurMisAJour = userRepository.save(user);

        return convertirEnDto(utilisateurMisAJour);
    }

    @Override
    public UserDto creerUtilisateurSiInexistant(UserRequest userRequest) {
        Optional<User> utilisateurExistant = userRepository.findFirstByEmail(userRequest.getEmail());

        if (utilisateurExistant.isPresent()) {
            return convertirEnDto(utilisateurExistant.get());
        }

        return creerUtilisateur(userRequest);
    }

    private UserDto convertirEnDto(User user) {
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setNom(user.getNom());
        userDto.setPrenom(user.getPrenom());
        userDto.setEmail(user.getEmail());
        userDto.setNumero_tel(user.getNumero_tel());
        userDto.setAdresse(user.getAdresse());
        userDto.setPassword(user.getPassword());
        return userDto;
    }
}