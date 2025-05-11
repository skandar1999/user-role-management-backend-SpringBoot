package com.skandar.gestion.repos;


import org.springframework.data.jpa.repository.JpaRepository;
import com.skandar.gestion.entities.Permission;

public interface PermissionRepository extends JpaRepository<Permission, Long> {
}