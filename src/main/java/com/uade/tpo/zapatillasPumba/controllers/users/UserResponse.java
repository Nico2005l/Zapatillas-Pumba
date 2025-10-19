package com.uade.tpo.zapatillasPumba.controllers.users;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class UserResponse {
    private Long id;
    private String username;
    private String firstName;
    private String lastName;
    private String email;
    private String role;
    private boolean isActive;
    private LocalDateTime createdAt;
}