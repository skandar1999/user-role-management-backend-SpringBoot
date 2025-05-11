package com.skandar.gestion.service;

import com.skandar.gestion.entities.Permission;
import com.skandar.gestion.entities.Role;
import com.skandar.gestion.entities.Utilisateur;
import com.skandar.gestion.repos.PermissionRepository;
import com.skandar.gestion.repos.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class RoleServiceImpl implements RoleService {
	
	@Autowired
    private RoleRepository roleRepository;

    @Override
    public List<Role> getAllRoles() {
        return roleRepository.findAll();
    }

    @Override
    public Role getRoleById(Long id) {
        return roleRepository.findById(id).orElse(null);
    }

    @Override
    public Role save(Role role) {
        return roleRepository.save(role);
    }

    @Override
    public Role updateRole(Role role) {
        return roleRepository.save(role);
    }

    @Override
    public void deleteRoleById(Long id) {
        roleRepository.deleteById(id);
    }

    @Override
    public List<Role> findByNomContaining(String nom) {
        return roleRepository.findByNomContaining(nom);
    }
	}
