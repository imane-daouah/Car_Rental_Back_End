package com.codewithprojects.spring.controller;


import com.codewithprojects.spring.dto.UserDto;
import com.codewithprojects.spring.dto.UserRequest;
import com.codewithprojects.spring.services.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {
    @Autowired
    private final UserService userService;



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


}