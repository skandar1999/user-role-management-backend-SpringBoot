package com.skandar.gestion.restcontrollers;

import com.skandar.gestion.entities.Role;
import com.skandar.gestion.entities.Permission;
import com.skandar.gestion.service.RoleService;
import com.skandar.gestion.service.PermissionService;
import com.skandar.gestion.service.HistoriqueActionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/roles")
@CrossOrigin(origins = "http://localhost:4200")
public class RoleRESTController {

    private static final Logger logger = LoggerFactory.getLogger(RoleRESTController.class);

    @Autowired
    private RoleService roleService;

    @Autowired
    private PermissionService permissionService;

    @Autowired
    private HistoriqueActionService historiqueActionService;

    @PostMapping
    public ResponseEntity<Role> createRole(@RequestBody Role role) {
        try {
            logger.info("Received role payload: {}", role);
            if (role.getNom() == null || role.getNom().isEmpty()) {
                logger.warn("Invalid role name: {}", role.getNom());
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            if (role.getPermissions() != null && !role.getPermissions().isEmpty()) {
                for (Permission permission : role.getPermissions()) {
                    Permission existingPermission = permissionService.getPermissionById(permission.getId());
                    if (existingPermission == null) {
                        logger.warn("Permission with ID {} not found", permission.getId());
                        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
                    }
                    permission.setNom(existingPermission.getNom());
                    permission.setDescription(existingPermission.getDescription());
                    permission.setRole(role);
                }
            }
            Role newRole = roleService.save(role);
            historiqueActionService.saveAction("Création du rôle avec ID " + newRole.getId(), newRole.getId());
            logger.info("Created role: {}", newRole);
            return new ResponseEntity<>(newRole, HttpStatus.CREATED);
        } catch (Exception e) {
            logger.error("Error creating role", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping
    public List<Role> getAllRoles() {
        List<Role> roles = roleService.getAllRoles();
        logger.info("Fetched roles: {}", roles);
        return roles;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Role> getRoleById(@PathVariable("id") Long id) {
        Role role = roleService.getRoleById(id);
        if (role == null) {
            logger.warn("Role with ID {} not found", id);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(role, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Role> updateRole(@PathVariable("id") Long id, @RequestBody Role role) {
        try {
            logger.info("Received update role payload for ID {}: {}", id, role);
            Role existingRole = roleService.getRoleById(id);
            if (existingRole == null) {
                logger.warn("Role with ID {} not found", id);
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            role.setId(id);
            if (role.getPermissions() != null) {
                for (Permission permission : role.getPermissions()) {
                    Permission existingPermission = permissionService.getPermissionById(permission.getId());
                    if (existingPermission == null) {
                        logger.warn("Permission with ID {} not found", permission.getId());
                        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
                    }
                    permission.setNom(existingPermission.getNom());
                    permission.setDescription(existingPermission.getDescription());
                    permission.setRole(role);
                }
            }
            Role updatedRole = roleService.updateRole(role);
            historiqueActionService.saveAction("Mise à jour du rôle avec ID " + updatedRole.getId(), updatedRole.getId());
            logger.info("Updated role: {}", updatedRole);
            return new ResponseEntity<>(updatedRole, HttpStatus.OK);
        } catch (Exception e) {
            logger.error("Error updating role", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRole(@PathVariable("id") Long id) {
        try {
            Role role = roleService.getRoleById(id);
            if (role == null) {
                logger.warn("Role with ID {} not found", id);
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            roleService.deleteRoleById(id);
            historiqueActionService.saveAction("Suppression du rôle avec ID " + id, id);
            logger.info("Deleted role with ID {}", id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            logger.error("Error deleting role", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/search/{nom}")
    public List<Role> searchRoleByName(@PathVariable("nom") String nom) {
        List<Role> roles = roleService.findByNomContaining(nom);
        logger.info("Searched roles with name {}: {}", nom, roles);
        return roles;
    }

    @PostMapping("/{roleId}/permissions/{permissionId}")
    public ResponseEntity<Role> addPermissionToRole(@PathVariable("roleId") Long roleId, @PathVariable("permissionId") Long permissionId) {
        try {
            Role role = roleService.getRoleById(roleId);
            Permission permission = permissionService.getPermissionById(permissionId);
            if (role == null || permission == null) {
                logger.warn("Role {} or Permission {} not found", roleId, permissionId);
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            role.getPermissions().add(permission);
            permission.setRole(role);
            Role updatedRole = roleService.updateRole(role);
            historiqueActionService.saveAction("Ajout de la permission " + permissionId + " au rôle " + roleId, roleId);
            logger.info("Added permission {} to role {}", permissionId, roleId);
            return new ResponseEntity<>(updatedRole, HttpStatus.OK);
        } catch (Exception e) {
            logger.error("Error adding permission to role", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{roleId}/permissions/{permissionId}")
    public ResponseEntity<Role> removePermissionFromRole(@PathVariable("roleId") Long roleId, @PathVariable("permissionId") Long permissionId) {
        try {
            Role role = roleService.getRoleById(roleId);
            Permission permission = permissionService.getPermissionById(permissionId);
            if (role == null || permission == null) {
                logger.warn("Role {} or Permission {} not found", roleId, permissionId);
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            if (permission.getRole() != null && permission.getRole().getId().equals(roleId)) {
                permission.setRole(null);
                permissionService.updatePermission(permission);
            } else {
                logger.warn("Permission {} is not associated with role {}", permissionId, roleId);
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            historiqueActionService.saveAction("Suppression de la permission " + permissionId + " du rôle " + roleId, roleId);
            logger.info("Removed permission {} from role {}", permissionId, roleId);
            Role updatedRole = roleService.getRoleById(roleId);
            return new ResponseEntity<>(updatedRole, HttpStatus.OK);
        } catch (Exception e) {
            logger.error("Error removing permission from role", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}