package com.skandar.gestion.restcontrollers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.skandar.gestion.entities.Role;
import com.skandar.gestion.service.RoleService;
import com.skandar.gestion.service.HistoriqueActionService;

@RestController
@RequestMapping("/api/roles")
@CrossOrigin
public class RoleRESTController {

    @Autowired
    RoleService roleService;

    @Autowired
    HistoriqueActionService historiqueActionService;

    @GetMapping
    public List<Role> getAllRoles() {
        return roleService.getAllRoles();
    }

    @GetMapping("/{id}")
    public Role getRoleById(@PathVariable("id") Long id) {
        return roleService.getRoleById(id);
    }

    @PostMapping
    public Role createRole(@RequestBody Role role) {
        Role createdRole = roleService.saveRole(role);
        historiqueActionService.saveAction("Created role: " + createdRole.getNom(), 1L);
        return createdRole;
    }

    @PutMapping("/{id}")
    public Role updateRole(@PathVariable("id") Long id, @RequestBody Role role) {
        role.setId(id);
        Role updatedRole = roleService.updateRole(role);
        historiqueActionService.saveAction("Updated role id: " + id, 1L);
        return updatedRole;
    }

    @DeleteMapping("/{id}")
    public void deleteRole(@PathVariable("id") Long id) {
        roleService.deleteRoleById(id);
        historiqueActionService.saveAction("Deleted role id: " + id, 1L);
    }

    @GetMapping("/search/{nom}")
    public List<Role> searchRoleByName(@PathVariable("nom") String nom) {
        return roleService.findByNomRoleContains(nom);
    }
}
