package com.uade.tpo.zapatillasPumba.controllers.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.uade.tpo.zapatillasPumba.entity.Role;

import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

        private final JwtAuthenticationFilter jwtAuthFilter;
        private final AuthenticationProvider authenticationProvider;

        @Bean
        public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
                http
                                .csrf(AbstractHttpConfigurer::disable)
                                .authorizeHttpRequests(req -> req
                                                // Rutas públicas para todos (autenticación y errores)
                                                .requestMatchers("/api/v1/auth/**", "/error/**").permitAll()
                                                
                                                // GUEST puede ver productos, categorías e imágenes
                                                .requestMatchers(HttpMethod.GET, "/products/**", "/categories/**", "/productImages/**").permitAll()
                                                
                                                // USER puede gestionar órdenes y ver descuentos
                                                .requestMatchers("/orders/**", "/order-items/**").hasAuthority(Role.USER.name())
                                                .requestMatchers(HttpMethod.GET, "/discounts/**").hasAuthority(Role.USER.name())
                                                
                                                // ADMIN puede hacer todo excepto crear órdenes y order items
                                                .requestMatchers(HttpMethod.POST, "/orders/**", "/order-items/**").hasAuthority(Role.USER.name())
                                                .requestMatchers(HttpMethod.PUT, "/orders/**", "/order-items/**").hasAuthority(Role.USER.name())
                                                
                                                // ADMIN tiene acceso a todo el resto
                                                .requestMatchers("/categories/**", "/products/**", "/productImages/**", "/discounts/**").hasAuthority(Role.ADMIN.name())
                                                
                                                // Cualquier otra ruta requiere autenticación
                                                .anyRequest().authenticated())
                                .sessionManagement(session -> session.sessionCreationPolicy(STATELESS))
                                .authenticationProvider(authenticationProvider)
                                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);

                return http.build();
        }
}
