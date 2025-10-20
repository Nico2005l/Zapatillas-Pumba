package com.uade.tpo.zapatillasPumba.controllers.users;

import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import java.util.List;

import com.uade.tpo.zapatillasPumba.entity.User;
import com.uade.tpo.zapatillasPumba.service.User.UserService;
import com.uade.tpo.zapatillasPumba.mapper.UserMapper;

@RestController
@RequestMapping("users")
public class UsersController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private UserMapper userMapper;

	@GetMapping
	public ResponseEntity<List<UserResponse>> getUsers() {
		List<User> userList = userService.getAllUsers();
		List<UserResponse> users = userList.stream()
				.map(user -> userMapper.toResponse(user))
				.toList();
		

		return ResponseEntity.ok(users);
	}

	@GetMapping("/{userId}")
	public ResponseEntity<UserResponse> getUserById(@PathVariable Long userId) {
		User user = userService.getUserById(userId);
		if (user != null) {
			UserResponse response = userMapper.toResponse(user);
			return ResponseEntity.ok(response);
		}
		return ResponseEntity.notFound().build();
	}


	@DeleteMapping("/{userId}")
	public ResponseEntity<String> deleteUser(@PathVariable Long userId) {
		userService.deleteUser(userId);
		return ResponseEntity.ok("Usuario eliminado correctamente");
	}

	@GetMapping("/me")
	public ResponseEntity<UserResponse> getCurrentUser(@RequestHeader("Authorization") String token) {
		System.out.println("Received token: " + token);
		if (token == null || !token.startsWith("Bearer ")) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
		}

    // Quita "Bearer " y espacios extra
    	token = token.substring(7).trim();
		User user = userService.getUserByToken(token);
		if (user != null) {
			UserResponse response = userMapper.toResponse(user);
			return ResponseEntity.ok(response);
		}
		return ResponseEntity.notFound().build();
	}

	// A futuro podemos implementar updateUser si es necesario, para eso estaba el UserRequest originalmente

}

