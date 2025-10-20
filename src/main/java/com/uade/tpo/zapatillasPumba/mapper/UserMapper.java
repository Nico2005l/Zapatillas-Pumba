package com.uade.tpo.zapatillasPumba.mapper;

import com.uade.tpo.zapatillasPumba.entity.Role;
import com.uade.tpo.zapatillasPumba.entity.User;
import com.uade.tpo.zapatillasPumba.controllers.users.UserResponse;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    public UserResponse toResponse(User user) {
        UserResponse response = new UserResponse();
        response.setId(user.getId());
        response.setUsername(user.getUsername());
        response.setFirstName(user.getFirstName());
        response.setLastName(user.getLastName());
        response.setEmail(user.getEmail());
        // Fix role mapping by getting the first role's authority
        response.setRole(user.getRole() != null ? user.getRole().name() : Role.USER.name());
        response.setActive(user.isEnabled());
        response.setCreatedAt(user.getCreatedAt());
        return response;
    }
}