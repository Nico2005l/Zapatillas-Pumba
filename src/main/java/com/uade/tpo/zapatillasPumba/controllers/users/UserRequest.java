package com.uade.tpo.zapatillasPumba.controllers.users;

import lombok.Data;

@Data
public class UserRequest {
	private String username;
	private String email;
	private String password;
	private String firstName;
	private String lastName;
	private String role;
}
