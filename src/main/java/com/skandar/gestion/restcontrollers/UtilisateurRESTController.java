package com.skandar.gestion.restcontrollers;

import com.skandar.gestion.entities.Utilisateur;
import com.skandar.gestion.service.UtilisateurService;
import com.skandar.gestion.service.HistoriqueActionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/utilisateurs")
@CrossOrigin
public class UtilisateurRESTController {

    @Autowired
    private UtilisateurService utilisateurService;

    @Autowired
    private HistoriqueActionService historiqueActionService;

    @GetMapping
    public List<Utilisateur> getAllUtilisateurs() {
        return utilisateurService.getAllUtilisateurs();
    }

    @GetMapping("/{id}")
    public Utilisateur getUtilisateurById(@PathVariable("id") Long id) {
        return utilisateurService.getUtilisateurById(id);
    }

    @PostMapping("/save")
    public Utilisateur createUtilisateur(@RequestBody Utilisateur utilisateur) {
        Utilisateur newUtilisateur = utilisateurService.saveUtilisateur(utilisateur);
        // Log the creation action
        historiqueActionService.saveAction("Création de l'utilisateur avec ID " + newUtilisateur.getId(), newUtilisateur.getId());
        return newUtilisateur;
    }

    @PutMapping("/update/{id}")
    public Utilisateur updateUtilisateur(@PathVariable("id") Long id, @RequestBody Utilisateur utilisateur) {
        utilisateur.setId(id);
        Utilisateur updatedUtilisateur = utilisateurService.updateUtilisateur(utilisateur);
        // Log the update action
        historiqueActionService.saveAction("Mise à jour de l'utilisateur avec ID " + updatedUtilisateur.getId(), updatedUtilisateur.getId());
        return updatedUtilisateur;
    }

    @DeleteMapping("/delete/{id}")
    public void deleteUtilisateur(@PathVariable("id") Long id) {
        Utilisateur utilisateur = utilisateurService.getUtilisateurById(id);  // get user first

        utilisateurService.deleteUtilisateurById(id);  // delete user
        historiqueActionService.saveAction("Suppression de l'utilisateur avec ID " + utilisateur.getId(), utilisateur.getId());
    }


    @GetMapping("/users/search/{nom}")
    public List<Utilisateur> searchUserByName(@PathVariable("nom") String nom) {
        return utilisateurService.findByNomContains(nom);
    }
}
