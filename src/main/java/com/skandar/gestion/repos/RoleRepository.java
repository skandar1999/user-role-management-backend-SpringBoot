package com.skandar.gestion.repos;

import com.skandar.gestion.entities.Role;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path = "rest")
public interface RoleRepository extends JpaRepository<Role, Long> {
    List<Role> findByNom(String nom);               // Exact match
    List<Role> findByNomContaining(String nom);     // Partial match
    List<Role> findByDescriptionContaining(String description);
}
