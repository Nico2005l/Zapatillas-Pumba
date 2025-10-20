package com.uade.tpo.zapatillasPumba.service.User;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uade.tpo.zapatillasPumba.controllers.config.JwtService;
import com.uade.tpo.zapatillasPumba.controllers.users.UserResponse;
import com.uade.tpo.zapatillasPumba.entity.User;
import com.uade.tpo.zapatillasPumba.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private JwtService jwtService;

    public UserServiceImpl() {
    }
    
    @Override
    public User getUserById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public User getUserByToken(String token) {
        String username = jwtService.extractUsername(token);
        System.out.println("Extracted username: " + username);
        return userRepository.findByUsername(username).orElse(null);
    }
}
