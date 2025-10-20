package com.uade.tpo.zapatillasPumba.service;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.uade.tpo.zapatillasPumba.controllers.auth.AuthenticationRequest;
import com.uade.tpo.zapatillasPumba.controllers.auth.AuthenticationResponse;
import com.uade.tpo.zapatillasPumba.controllers.auth.RegisterRequest;
import com.uade.tpo.zapatillasPumba.controllers.config.JwtService;
import com.uade.tpo.zapatillasPumba.entity.Role;
import com.uade.tpo.zapatillasPumba.entity.User;
import com.uade.tpo.zapatillasPumba.repository.UserRepository;

import lombok.RequiredArgsConstructor;

/* Con estas anotaciones le indicamos a Spring que esta clase es un servicio
y que se debe crear una instancia de ella cuando sea necesaria */
@Service
@RequiredArgsConstructor


public class AuthenticationService {
        private final UserRepository repository;
        private final PasswordEncoder passwordEncoder;
        private final JwtService jwtService;
        private final AuthenticationManager authenticationManager;

        

        /* Al registrarse, agarra el request mandado y crea un objeto User con sus datos */
        public AuthenticationResponse register(RegisterRequest request) {
                var user = User.builder() // Podemos usar el patron builder porque usamos la anotacion @Builder en la clase User
                                .firstName(request.getFirstname()) //Va uno por uno seteando los atributos
                                .lastName(request.getLastname())
                                .email(request.getEmail())
                                .username(request.getUsername())
                                .password(passwordEncoder.encode(request.getPassword())) // Aca es donde encriptamoos la contraseña
                                .role(request.getRole())
                                .isActive(true)
                                .createdAt(java.time.LocalDateTime.now())
                                .build();

                repository.save(user); // Guardamos el usuario en la base de datos
                var jwtToken = jwtService.generateToken(user); // Generamos el token JWT para el usuario
                
                return AuthenticationResponse.builder()
                                .accessToken(jwtToken)
                                .build();
        }
        

        // Al logearse, primero chequea que el usuario exista en la base de datos
        public AuthenticationResponse authenticate(AuthenticationRequest request) {
                var user = repository.findByEmail(request.getEmail()) // buscamos el usuario por email para poder tener sus datos
                                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

                user.setRole(Role.USER);
                
                // al tener los lados, autenticamos el usuario con el AuthenticationManager
                authenticationManager.authenticate(
                                new UsernamePasswordAuthenticationToken(
                                                user.getUsername(), // Usamos el username para autenticar
                                                request.getPassword()));

                // Si llegó hasta acá, es porque el username y password son correctos
                
                var jwtToken = jwtService.generateToken(user);
                return AuthenticationResponse.builder()
                                .accessToken(jwtToken)
                                .build();
        }
}
