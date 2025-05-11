package com.skandar.gestion.service;

import com.skandar.gestion.entities.Permission;
import java.util.List;

public interface PermissionService {

    Permission savePermission(Permission permission);           // Create a permission
    Permission updatePermission(Permission permission);         // Update a permission
    void deletePermission(Long id);                            // Delete a permission by ID
    Permission getPermissionById(Long id);                      // Get a permission by ID
    List<Permission> getAllPermissions();                       // List all permissions
    
    

}
