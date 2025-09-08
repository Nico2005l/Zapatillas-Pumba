package com.uade.tpo.zapatillasPumba.service;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.uade.tpo.zapatillasPumba.controllers.auth.AuthenticationRequest;
import com.uade.tpo.zapatillasPumba.controllers.auth.AuthenticationResponse;
import com.uade.tpo.zapatillasPumba.controllers.auth.RegisterRequest;
import com.uade.tpo.zapatillasPumba.controllers.config.JwtService;
import com.uade.tpo.zapatillasPumba.entity.User;
import com.uade.tpo.zapatillasPumba.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
        private final UserRepository repository;
        private final PasswordEncoder passwordEncoder;
        private final JwtService jwtService;
        private final AuthenticationManager authenticationManager;

        public AuthenticationResponse register(RegisterRequest request) {
                var user = User.builder()
                                .firstName(request.getFirstname())
                                .lastName(request.getLastname())
                                .email(request.getEmail())
                                .username(request.getUsername())
                                .password(passwordEncoder.encode(request.getPassword()))
                                .role(request.getRole())
                                .isActive(true)
                                .createdAt(java.time.LocalDateTime.now())
                                .build();

                repository.save(user);
                var jwtToken = jwtService.generateToken(user);
                return AuthenticationResponse.builder()
                                .accessToken(jwtToken)
                                .build();
        }

        public AuthenticationResponse authenticate(AuthenticationRequest request) {
                authenticationManager.authenticate(
                                new UsernamePasswordAuthenticationToken(
                                                request.getUsername(),
                                                request.getPassword()));

                var user = repository.findByUsername(request.getUsername())
                                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
                var jwtToken = jwtService.generateToken(user);
                return AuthenticationResponse.builder()
                                .accessToken(jwtToken)
                                .build();
        }
}
