package com.uade.tpo.zapatillasPumba.service.User;

import java.util.List;

import com.uade.tpo.zapatillasPumba.entity.User;

public interface UserService {

    User getUserById(Long id);
    List<User> getAllUsers();
    void updateUser(Long id, User user);
    void deleteUser(Long id);

}
