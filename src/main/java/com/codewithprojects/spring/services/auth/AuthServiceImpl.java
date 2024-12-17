package com.codewithprojects.spring.services.auth;

import com.codewithprojects.spring.dto.AdminDto;
import com.codewithprojects.spring.dto.LoginRequest;
import com.codewithprojects.spring.dto.SignupRequest;
import com.codewithprojects.spring.dto.UserDto;
import com.codewithprojects.spring.entity.Admin;
import com.codewithprojects.spring.entity.Personne;
import com.codewithprojects.spring.entity.User;
import com.codewithprojects.spring.repository.AdminRepository;
import com.codewithprojects.spring.repository.PersonneRepository;
import com.codewithprojects.spring.repository.UserRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;


import org.springframework.security.crypto.password.PasswordEncoder;
@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService{
	@Autowired
	private UserRespository userReepository;
	@Autowired
    private PasswordEncoder passwordEncoder;
	@Autowired
	private AdminRepository adminReepository;
    @Autowired
    private PersonneRepository personneRepository;

	@Override
	public UserDto createCustomer(SignupRequest signupRequest) {
		User user=new User();
		user.setNom(signupRequest.getNom());
		user.setPrenom(signupRequest.getPrenom());
		user.setEmail(signupRequest.getEmail());
		user.setPassword(passwordEncoder.encode(signupRequest.getPassword()));
		user.setAdresse(signupRequest.getAdresse());
		user.setNumero_tel(signupRequest.getNumero_tel());
		User creerUser = userReepository.save(user);
		UserDto userDto=new UserDto();
		userDto.setId(creerUser.getId());
		return userDto;
	}

	@Override
	public boolean hasCustomerWithEmail(String email) {
		
		return userReepository.findFirstByEmail(email).isPresent();
	}

	@Override
	public UserDto loginCustomer(LoginRequest loginRequest) {
		User user = userReepository.findFirstByEmail(loginRequest.getEmail())
	            .orElseThrow(() -> new IllegalArgumentException("Invalid email or password"));

	        if (!passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())) {
	            throw new IllegalArgumentException("Invalid email or password");
	        }

	        UserDto userDto = new UserDto();
	        userDto.setId(user.getId());
	        userDto.setEmail(user.getEmail());
	        userDto.setNom(user.getNom());
	        userDto.setPrenom(user.getPrenom());
	        userDto.setNumero_tel(user.getNumero_tel());
	        userDto.setAdresse(user.getAdresse());
	        return userDto;
	}

	@Override
	public AdminDto createAdmin(SignupRequest signupRequest) {
		Admin user=new Admin();
		user.setNom(signupRequest.getNom());
		user.setPrenom(signupRequest.getPrenom());
		user.setEmail(signupRequest.getEmail());
		user.setPassword(passwordEncoder.encode(signupRequest.getPassword()));
		user.setAdresse(signupRequest.getAdresse());
		user.setNumero_tel(signupRequest.getNumero_tel());
		Admin creerUser = adminReepository.save(user);
		AdminDto userDto=new AdminDto();
		userDto.setId(creerUser.getId());
		return userDto;
	}

	@Override
	public UserDto login(LoginRequest loginRequest) {
	    Personne personne = personneRepository.findByEmail(loginRequest.getEmail())
	    		 .orElseThrow(() -> new IllegalArgumentException("Invalid email or password"));

        if (!passwordEncoder.matches(loginRequest.getPassword(), personne.getPassword())) {
            throw new IllegalArgumentException("Invalid email or password");
        }

	    // Construire et retourner le DTO en fonction du type de Personne
	    String role = (personne instanceof Admin) ? "Admin" : "Client";
	    
	    
	    UserDto userDto = new UserDto();
        userDto.setId(personne.getId());
        userDto.setEmail(personne.getEmail());
        userDto.setNom(personne.getNom());
        userDto.setPrenom(personne.getPrenom());
        userDto.setNumero_tel(personne.getNumero_tel());
        userDto.setAdresse(personne.getAdresse());
        userDto.setRole(role);
        userDto.setPassword(personne.getPassword());
        return userDto;
	}

	
	
	
}

