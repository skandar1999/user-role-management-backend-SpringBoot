package com.skandar.gestion.service;

import com.skandar.gestion.entities.HistoriqueAction;
import com.skandar.gestion.entities.Utilisateur;
import com.skandar.gestion.repos.HistoriqueActionRepository;
import com.skandar.gestion.repos.UtilisateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class HistoriqueActionService {

    @Autowired
    private HistoriqueActionRepository historiqueActionRepository;

    @Autowired
    private UtilisateurRepository utilisateurRepository; 

    public void saveAction(String actionDescription, Long utilisateurId) {
        Utilisateur utilisateur = utilisateurRepository.findById(utilisateurId)
                .orElse(null);

        HistoriqueAction historique = new HistoriqueAction();
        historique.setAction(actionDescription);
        historique.setDate(LocalDateTime.now());
        historique.setUtilisateur(utilisateur);

        historiqueActionRepository.save(historique);
    }

    // ✅ This method was missing!
    public List<HistoriqueAction> getAllActions() {
        return historiqueActionRepository.findAll();
    }

    // ✅ This method was missing!
    public HistoriqueAction getActionById(Long id) {
        Optional<HistoriqueAction> optional = historiqueActionRepository.findById(id);
        return optional.orElse(null);
    }

    // ✅ This method was missing!
    public void deleteActionById(Long id) {
        historiqueActionRepository.deleteById(id);
    }
}
