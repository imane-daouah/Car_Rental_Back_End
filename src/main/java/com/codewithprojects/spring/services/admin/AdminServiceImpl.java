package com.codewithprojects.spring.services.admin;

import com.codewithprojects.spring.dto.AdminDto;
import com.codewithprojects.spring.dto.UserRequest;
import com.codewithprojects.spring.entity.Admin;
import com.codewithprojects.spring.repository.AdminRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {
		@Autowired
		private PasswordEncoder passwordEncoder;
		@Autowired
		private AdminRepository adminReepository;
		@Override
		public AdminDto modifierMotDePasseAvecVerification(Long id, String ancienMotDePasse, String nouveauMotDePasse) {
			Admin admin = adminReepository.findById(id).orElseThrow(() -> new RuntimeException("Admin introuvable"));

			if (ancienMotDePasse == null || ancienMotDePasse.isEmpty() || nouveauMotDePasse == null || nouveauMotDePasse.isEmpty()) {
				throw new IllegalArgumentException("Les mots de passe ne peuvent pas être vides.");
			}

			// Vérification de l'ancien mot de passe
			if (!passwordEncoder.matches(ancienMotDePasse, admin.getPassword())) {
				throw new IllegalArgumentException("L'ancien mot de passe est incorrect.");
			}

			// Mise à jour avec le nouveau mot de passe encodé
			admin.setPassword(passwordEncoder.encode(nouveauMotDePasse));
			Admin adminMisAJour = adminReepository.save(admin);

			return convertirEnDto(adminMisAJour);
		}
		private AdminDto convertirEnDto(Admin user) {
			AdminDto userDto = new AdminDto();
			userDto.setId(user.getId());
			userDto.setNom(user.getNom());
			userDto.setPrenom(user.getPrenom());
			userDto.setEmail(user.getEmail());
			userDto.setNumero_tel(user.getNumero_tel());
			userDto.setAdresse(user.getAdresse());
			userDto.setPassword(user.getPassword());
			return userDto;
		}
		@Override
		public AdminDto modifierUtilisateur(Long id, UserRequest userRequest) {
			Admin user = adminReepository.findById(id).orElseThrow(() -> new RuntimeException("Admin introuvable"));

			user.setNom(userRequest.getNom());
			user.setPrenom(userRequest.getPrenom());
			user.setEmail(userRequest.getEmail());
			user.setNumero_tel(userRequest.getNumero_tel());
			user.setAdresse(userRequest.getAdresse());


			Admin  adminModifie = adminReepository.save(user);

			return convertirEnDto(adminModifie);
		}
	}

