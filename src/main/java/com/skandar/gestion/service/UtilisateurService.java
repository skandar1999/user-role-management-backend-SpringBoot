package com.skandar.gestion.service;


import java.util.List;
import com.skandar.gestion.entities.Utilisateur;

public interface UtilisateurService {

    Utilisateur saveUtilisateur(Utilisateur u);
    Utilisateur updateUtilisateur(Utilisateur u);
    void deleteUtilisateur(Utilisateur u);
    void deleteUtilisateurById(Long id);
    Utilisateur getUtilisateurById(Long id);
    List<Utilisateur> getAllUtilisateurs();
    List<Utilisateur> findByNomContains(String nom);
}