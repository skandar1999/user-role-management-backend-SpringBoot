package com.skandar.gestion;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.skandar.gestion.entities.Role;
import com.skandar.gestion.entities.Utilisateur;
import com.skandar.gestion.repos.RoleRepository;
import com.skandar.gestion.repos.UtilisateurRepository;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;  // Correct → Liste générique pour les collections

@SpringBootTest
class GestionUtilisateursRoleApplicationTests {

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UtilisateurRepository utilisateurRepository;

   
    
    @Test
    public void testFindRole() {
       
        Role foundRole = roleRepository.findById(3L).get();

        
        System.out.println(foundRole);
    }
    
    @Test
    public void testDeleteRole()
    {
    	roleRepository.deleteById(4L);;
    }
    
    @Test
    public void testUpdateRole()
    {
    Role p = roleRepository.findById(1L).get();
    p.setDescription("Description Admin Test");
    roleRepository.save(p);
    }

   
    @Test
    public void testListerTousLesRole() {
        List<Role> roles = roleRepository.findAll();

        for (Role r : roles) {
            System.out.println(r);
        }
        
    }
    
    
    
    @Test
    public void testCreateUtilisateur() {
        Role role = roleRepository.findById(2L).orElse(null);
        assertNotNull(role, "Role avec id=1 introuvable");

        Utilisateur user = new Utilisateur();
        user.setNom("sabri");
        user.setEmail("sabri@gmail.com");
        user.setMotDePasse("123456789");
        user.setRole(role); // Affectation correcte
        user.setActif(true);

        Utilisateur savedUser = utilisateurRepository.save(user);
        System.out.println(savedUser);
    }
    
    @Test
    public void testUpdateUtilisateur() {
        Utilisateur user = utilisateurRepository.findById(1L).orElse(null);
        assertNotNull(user, "Utilisateur avec id=1 introuvable");

        user.setNom("Skandar Modifié");
        user.setEmail("skandar.updated@gmail.com");

        Utilisateur updatedUser = utilisateurRepository.save(user);

        System.out.println(updatedUser);
    }
    
    @Test
    public void testAfficherUtilisateurById() {
        Utilisateur user = utilisateurRepository.findById(1L).orElse(null);

        if (user != null) {
            System.out.println("Id         : " + user.getId());
            System.out.println("Nom        : " + user.getNom());
            System.out.println("Email      : " + user.getEmail());
            System.out.println("Mot de passe : " + user.getMotDePasse());
            System.out.println("Role       : " + user.getRole().getNom());
            System.out.println("Actif      : " + user.isActif());
        } else {
            System.out.println("Aucun utilisateur trouvé avec id=1");
        }
    }

    	

    @Test
    public void testDeleteUtilisateur() {
        Long id = 1L;  
        
        utilisateurRepository.deleteById(id);
        
        Utilisateur user = utilisateurRepository.findById(id).orElse(null);
        
        if (user == null) {
            System.out.println("Utilisateur avec id " + id + " supprimé avec succès.");
        } else {
            System.out.println("Echec de suppression !");
        }
    }

    
    @Test
    public void testFindUtilisateursWithRoleAdmin() {
        List<Utilisateur> utilisateursAdmin = utilisateurRepository.findByRole_Nom("ADMIN");

        for (Utilisateur u : utilisateursAdmin) {
            // Print the user details or assert something
            System.out.println(u);
        }
    }

   


}
