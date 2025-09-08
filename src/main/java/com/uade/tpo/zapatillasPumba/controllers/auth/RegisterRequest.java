package com.uade.tpo.zapatillasPumba.controllers.auth;

import com.uade.tpo.zapatillasPumba.entity.Role;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {

    private String firstname;
    private String lastname;
    private String email;
    private String password;
    private String username;
    private Role role;
}
