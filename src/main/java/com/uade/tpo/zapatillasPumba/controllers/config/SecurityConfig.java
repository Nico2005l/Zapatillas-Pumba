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
import org.springframework.web.cors.CorsConfiguration;

import com.uade.tpo.zapatillasPumba.entity.Role;

import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

import java.util.List;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

        private final JwtAuthenticationFilter jwtAuthFilter;
        private final AuthenticationProvider authenticationProvider;

        @Bean
        public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {


                        http.cors(cors -> cors.configurationSource(request -> {
                        var corsConfig = new CorsConfiguration();
                        corsConfig.setAllowedOrigins(List.of("http://localhost:5173"));
                        corsConfig.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));
                        corsConfig.setAllowedHeaders(List.of("Authorization", "Content-Type"));
                        corsConfig.setExposedHeaders(List.of("Authorization"));
                        corsConfig.setAllowCredentials(true);
                        return corsConfig;
                }))
                                .csrf(AbstractHttpConfigurer::disable)
                                .authorizeHttpRequests(req -> req

                                                .requestMatchers("/**").permitAll()
                                                // Rutas públicas para todos (autenticación y errores)
                                                .requestMatchers("/auth/**", "/error/**").permitAll()
                                                
                                                
                                                // GUEST puede ver productos, categorías e imágenes
                                                .requestMatchers(HttpMethod.GET, "/products/**", "/categories/**", "/productImages/**").permitAll()
                                                
                                                // GET a descuentos - permitido para USER y ADMIN
                                                .requestMatchers(HttpMethod.GET, "/discounts/**").hasAnyAuthority(Role.USER.name(), Role.ADMIN.name())
                                                
                                                // USER puede gestionar órdenes
                                                .requestMatchers(HttpMethod.POST, "/orders/**", "/order-items/**").hasAuthority(Role.USER.name())
                                                .requestMatchers(HttpMethod.GET, "/orders/**", "/order-items/**").hasAnyAuthority(Role.USER.name(), Role.ADMIN.name())

                                                // CAMBIAR EN UN PUNTO ES TEMPORAL !!!!!!!!!!!!!
                                                .requestMatchers("/carts/**").permitAll()
                                                .requestMatchers("/pedidos/**").permitAll()

                                                // ADMIN tiene acceso a todo el resto (modificación de productos, categorías, imágenes)                                                .requestMatchers(HttpMethod.GET, "/users/**").hasAuthority(Role.ADMIN.name())
                                                .requestMatchers(HttpMethod.GET, "/users/**").permitAll()
                                                .requestMatchers(HttpMethod.POST, "/categories/**", "/products/**", "/productImages/**", "/discounts/**").hasAuthority(Role.ADMIN.name())
                                                .requestMatchers(HttpMethod.PUT, "/categories/**", "/products/**", "/productImages/**", "/discounts/**").hasAuthority(Role.ADMIN.name())
                                                .requestMatchers(HttpMethod.DELETE,"/users/**", "/categories/**", "/products/**", "/productImages/**", "/discounts/**", "/orders/**", "/order-items/**").hasAuthority(Role.ADMIN.name())
                                                
                                                // Cualquier otra ruta requiere autenticación
                                                .anyRequest().authenticated())
                                                
                                .sessionManagement(session -> session.sessionCreationPolicy(STATELESS))
                                .authenticationProvider(authenticationProvider)
                                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);
                                

                return http.build();
        }
}
