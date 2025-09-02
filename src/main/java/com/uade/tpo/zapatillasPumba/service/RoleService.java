package com.uade.tpo.zapatillasPumba.service;

import java.util.List;

import com.uade.tpo.zapatillasPumba.entity.Role;

public interface RoleService {

    void createRole(Role role);
    Role getRoleById(Long id);
    List<Role> getAllRoles();
    void updateRole(Long id, Role role);
    void deleteRole(Long id);

}
