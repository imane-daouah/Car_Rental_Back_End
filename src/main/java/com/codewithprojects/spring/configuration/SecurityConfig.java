package com.example.demo.configuration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {
	 @Bean
	    public PasswordEncoder passwordEncoder() {
	        return new BCryptPasswordEncoder();
	    }

  /*  @Bean
    public UserDetailsService userDetailsService(PasswordEncoder passwordEncoder) {
        UserDetails user = User.builder()
                .username("admin")
                .password(passwordEncoder.encode("password")) // Définit un mot de passe encodé
                .roles("ADMIN") // Rôle de l'utilisateur
                .build();

        return new InMemoryUserDetailsManager(user);
    }*/
	 @Bean
	    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
/*		 http.csrf().disable()
    .authorizeRequests()
    .anyRequest().permitAll(); // Toutes les requêtes sont accessibles sans authentification
	     */ http
	            .csrf(csrf -> csrf.disable()) // Désactive CSRF (attention en production)
	            .authorizeHttpRequests(auth -> auth
	                .requestMatchers("/api/auth/**").permitAll() // Permet l'accès libre aux routes d'authentification
	               
	                .requestMatchers("/api/cars/**").permitAll()
	                .requestMatchers("/api/users/**").permitAll()
	                .requestMatchers("/api/admin/**").permitAll()
	                .requestMatchers("/api/reservations/**").permitAll()
	                .requestMatchers("/api/factures/**").permitAll()
	                .anyRequest().authenticated()               // Toutes les autres requêtes nécessitent une authentification
	               
	            );

	        return http.build();
	    }
}