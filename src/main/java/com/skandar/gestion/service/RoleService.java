package com.skandar.gestion.service;

import com.skandar.gestion.entities.Role;
import java.util.List;

public interface RoleService {
    List<Role> getAllRoles();
    Role getRoleById(Long id);
    Role save(Role role);
    Role updateRole(Role role);
    void deleteRoleById(Long id);
    List<Role> findByNomContaining(String nom);
}