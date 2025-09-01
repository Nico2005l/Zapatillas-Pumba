package com.uade.tpo.zapatillasPumba.User;


import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImplementation implements UserService {

    private final UserRepository userRepository;

    public UserServiceImplementation(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User createUser(User user) {
        // Later, you will add password hashing here before saving
        if (userRepository.findByUsername(user.getUsername()).isPresent()) {
            throw new IllegalStateException("Username already exists");
        }
        return userRepository.save(user);
    }

    @Override
    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public void deleteUser(Long id) {
        if (!userRepository.existsById(id)) {
            throw new IllegalStateException("User with id " + id + " does not exist");
        }
        userRepository.deleteById(id);
    }

    @Override
    public User updateUser(Long id, User userUpdates) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException("User with id " + id + " does not exist"));

        if (userUpdates.getUsername() != null && !userUpdates.getUsername().equals(user.getUsername())) {
            if (userRepository.findByUsername(userUpdates.getUsername()).isPresent()) {
                throw new IllegalStateException("Username already exists");
            }
            user.setUsername(userUpdates.getUsername());
        }

        if (userUpdates.getPassword() != null && !userUpdates.getPassword().equals(user.getPassword())) {
            // Here you should add password hashing
            user.setPassword(userUpdates.getPassword());
        }

        return userRepository.save(user);
    }
}
