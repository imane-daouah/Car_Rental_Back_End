package com.codewithprojects.spring.controller;

import com.codewithprojects.spring.dto.AdminDto;
import com.codewithprojects.spring.dto.UserRequest;
import com.codewithprojects.spring.services.admin.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;



import lombok.RequiredArgsConstructor;


@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
public class AdminController {
	@Autowired
	private  AdminService adminService;

	@PutMapping("/{userId}/changer-mot-de-passe")
	public ResponseEntity<AdminDto> changerMotDePasse(
			@PathVariable Long userId,
			@RequestParam String ancienMotDePasse,
			@RequestParam String nouveauMotDePasse) {
		AdminDto adminMisAJour = adminService.modifierMotDePasseAvecVerification(userId, ancienMotDePasse, nouveauMotDePasse);
		return ResponseEntity.ok(adminMisAJour);
	}

	@PutMapping("/modifier/{id}")
	public ResponseEntity<AdminDto> updateUser(@PathVariable Long id, @RequestBody UserRequest userRequest) {
		AdminDto updatedUser = adminService.modifierUtilisateur(id, userRequest);
		return ResponseEntity.ok(updatedUser);
	}

}
