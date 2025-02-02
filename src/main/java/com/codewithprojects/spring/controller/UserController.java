package com.codewithprojects.spring.controller;

import com.codewithprojects.spring.dto.UserDto;
import com.codewithprojects.spring.dto.UserRequest;
import com.codewithprojects.spring.services.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import lombok.RequiredArgsConstructor;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {
    @Autowired
    private UserService userService;



    @PutMapping("/modifier/{id}")
    public ResponseEntity<UserDto> updateUser(@PathVariable Long id, @RequestBody UserRequest userRequest) {
        UserDto updatedUser = userService.modifierUtilisateur(id, userRequest);
        return ResponseEntity.ok(updatedUser);
    }

    @DeleteMapping("/supprimer/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.supprimerUtilisateur(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/tous")
    public ResponseEntity<List<UserDto>> getAllUsers() {
        List<UserDto> usersList = userService.getTousUtilisateurs();
        return ResponseEntity.ok(usersList);
    }

    @PutMapping("/modifierMotDePasse/{id}")
    public ResponseEntity<UserDto> updatePassword(@PathVariable Long id, @RequestBody String nouveauMotDePasse) {
        UserDto updatedUser = userService.modifierMotDePasse(id, nouveauMotDePasse);
        return ResponseEntity.ok(updatedUser);
    }
    @GetMapping("/count")
    public long getNombreClients() {
        return userService.getNombreUtilisateurs();
    }
    @PutMapping("/utilisateur/{userId}/changer-mot-de-passe")
    public ResponseEntity<UserDto> changerMotDePasse(
            @PathVariable Long userId,
            @RequestParam String ancienMotDePasse,
            @RequestParam String nouveauMotDePasse) {
        UserDto utilisateurMisAJour = userService.modifierMotDePasseAvecVerification(userId, ancienMotDePasse, nouveauMotDePasse);
        return ResponseEntity.ok(utilisateurMisAJour);
    }
}