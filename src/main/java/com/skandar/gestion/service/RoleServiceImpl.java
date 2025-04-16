package com.skandar.gestion.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skandar.gestion.entities.Role;
import com.skandar.gestion.repos.RoleRepository;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public Role saveRole(Role r) {
        return roleRepository.save(r);
    }

    @Override
    public Role updateRole(Role r) {
        return roleRepository.save(r);
    }

    @Override
    public void deleteRole(Role r) {
        roleRepository.delete(r);
    }

    @Override
    public void deleteRoleById(Long id) {
        roleRepository.deleteById(id);
    }

    @Override
    public Role getRoleById(Long id) {
        return roleRepository.findById(id).orElse(null);
    }

    @Override
    public List<Role> getAllRoles() {
        return roleRepository.findAll();
    }

    @Override
    public List<Role> findByNomRole(String nom) {
        return roleRepository.findByNom(nom);  
    }

    @Override
    public List<Role> findByNomRoleContains(String nom) {
        return roleRepository.findByNomContaining(nom); 
    }

    @Override
    public List<Role> findByDescriptionRole(String description) {
        return roleRepository.findByDescriptionContaining(description); 
    }
}
