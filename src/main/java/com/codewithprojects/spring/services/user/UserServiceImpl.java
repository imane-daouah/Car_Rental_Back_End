package com.codewithprojects.spring.services.user;

import com.codewithprojects.spring.dto.UserDto;
import com.codewithprojects.spring.dto.UserRequest;
import com.codewithprojects.spring.entity.User;
import com.codewithprojects.spring.repository.UserRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    @Autowired
    private  UserRespository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Override
    public UserDto creerUtilisateur(UserRequest userRequest) {
        User user = new User();
        user.setNom(userRequest.getNom());
        user.setPrenom(userRequest.getPrenom());
        user.setEmail(userRequest.getEmail());
        user.setNumero_tel(userRequest.getNumero_tel());
        user.setAdresse(userRequest.getAdresse());
        user.setPassword(passwordEncoder.encode(userRequest.getPassword())); // Enregistrer le mot de passe

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
    @Override
    public long getNombreUtilisateurs() {
        // Utilisation du repository pour compter les utilisateurs
        return userRepository.count();
    }

    @Override
    public UserDto modifierMotDePasseAvecVerification(Long id, String ancienMotDePasse, String nouveauMotDePasse) {
        User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("Utilisateur introuvable"));

        if (ancienMotDePasse == null || ancienMotDePasse.isEmpty() || nouveauMotDePasse == null || nouveauMotDePasse.isEmpty()) {
            throw new IllegalArgumentException("Les mots de passe ne peuvent pas être vides.");
        }

        // Vérification de l'ancien mot de passe
        if (!passwordEncoder.matches(ancienMotDePasse, user.getPassword())) {
            throw new IllegalArgumentException("L'ancien mot de passe est incorrect.");
        }

        // Mise à jour avec le nouveau mot de passe encodé
        user.setPassword(passwordEncoder.encode(nouveauMotDePasse));
        User utilisateurMisAJour = userRepository.save(user);

        return convertirEnDto(utilisateurMisAJour);
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