package com.skandar.gestion.restcontrollers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.skandar.gestion.entities.Role;
import com.skandar.gestion.service.RoleService;

@RestController
@RequestMapping("/api/roles")
@CrossOrigin
public class RoleRESTController {

    @Autowired
    RoleService roleService;

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
        return roleService.saveRole(role);
    }

    @PutMapping("/{id}")
    public Role updateRole(@PathVariable("id") Long id, @RequestBody Role role) {
        role.setId(id);
        return roleService.updateRole(role);
    }

    @DeleteMapping("/{id}")
    public void deleteRole(@PathVariable("id") Long id) {
        roleService.deleteRoleById(id);
    }
    
    
    @GetMapping("/search/{nom}")
    public List<Role> searchRoleByName(@PathVariable("nom") String nom){
        return roleService.findByNomRoleContains(nom);
    }

}
