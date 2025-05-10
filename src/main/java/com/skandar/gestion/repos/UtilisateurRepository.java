package com.skandar.gestion.repos;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import com.skandar.gestion.entities.Utilisateur;


@RepositoryRestResource(path = "rest")
public interface UtilisateurRepository extends JpaRepository<Utilisateur, Long> {
	
    Utilisateur findByemail(String email);
    List<Utilisateur> findByRoleId(Long roleId);
    List<Utilisateur> findByRole_Nom(String roleNom);
	List<Utilisateur> findByNomContains(String nom);

}