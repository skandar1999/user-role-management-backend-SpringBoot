package com.skandar.gestion.restcontrollers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.skandar.gestion.entities.Utilisateur;
import com.skandar.gestion.service.UtilisateurService;

@RestController
@RequestMapping("/api/utilisateurs")
@CrossOrigin
public class UtilisateurRESTController {

    @Autowired
    UtilisateurService utilisateurService;

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
        return utilisateurService.saveUtilisateur(utilisateur);
    }

    @PutMapping("/update/{id}")
    public Utilisateur updateUtilisateur(@PathVariable("id") Long id, @RequestBody Utilisateur utilisateur) {
        utilisateur.setId(id);
        return utilisateurService.updateUtilisateur(utilisateur);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteUtilisateur(@PathVariable("id") Long id) {
        utilisateurService.deleteUtilisateurById(id);
    }
}
