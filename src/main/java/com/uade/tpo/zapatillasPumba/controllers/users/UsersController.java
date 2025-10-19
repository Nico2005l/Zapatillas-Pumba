package com.uade.tpo.zapatillasPumba.controllers.users;

import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import java.util.List;
import java.util.stream.Collectors;

import com.uade.tpo.zapatillasPumba.entity.User;
import com.uade.tpo.zapatillasPumba.mapper.UserMapper;
import com.uade.tpo.zapatillasPumba.service.User.UserService;
import com.uade.tpo.zapatillasPumba.controllers.common.DeleteResponse;

@RestController
@RequestMapping("users")
public class UsersController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserMapper userMapper;

    @GetMapping
    public ResponseEntity<List<UserResponse>> getUsers() {
        List<UserResponse> users = userService.getAllUsers().stream()
            .map(userMapper::toResponse)
            .collect(Collectors.toList());
        return ResponseEntity.ok(users);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserResponse> getUserById(@PathVariable Long userId) {
        User user = userService.getUserById(userId);
        if (user != null) {
            return ResponseEntity.ok(userMapper.toResponse(user));
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<DeleteResponse> deleteUser(@PathVariable Long userId) {
        userService.deleteUser(userId);
        return ResponseEntity.ok(new DeleteResponse("El usuario se ha borrado correctamente"));
    }
}

