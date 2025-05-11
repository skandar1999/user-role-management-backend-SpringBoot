package com.skandar.gestion.restcontrollers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skandar.gestion.entities.Permission;
import com.skandar.gestion.service.PermissionService;

import java.util.List;

@RestController
public class PermissionController {

    @Autowired
    private PermissionService permissionService;

    @GetMapping("/api/permissions")
    public List<Permission> getAllPermissions() {
        return permissionService.getAllPermissions();
    }
    
    
}
