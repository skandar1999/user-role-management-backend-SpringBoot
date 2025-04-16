package com.skandar.gestion.service;

import java.util.List;
import com.skandar.gestion.entities.Role;

public interface RoleService {

    Role saveRole(Role r);           // Create a role
    Role updateRole(Role r);         // Update a role
    void deleteRole(Role r);         // Delete a role by Role object
    void deleteRoleById(Long id);    // Delete a role by ID
    Role getRoleById(Long id);       // Get a role by ID
    List<Role> getAllRoles();        // List all roles
    List<Role> findByNomRole(String nom);  // Find roles by exact name
    List<Role> findByNomRoleContains(String nom); // Find roles by partial name match
    List<Role> findByDescriptionRole(String description); // Find roles by description
}
