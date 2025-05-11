package com.skandar.gestion.service;

import com.skandar.gestion.entities.HistoriqueAction;
import com.skandar.gestion.entities.Utilisateur;
import com.skandar.gestion.repos.HistoriqueActionRepository;
import com.skandar.gestion.repos.UtilisateurRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class HistoriqueActionService {

    private static final Logger logger = LoggerFactory.getLogger(HistoriqueActionService.class);

    @Autowired
    private HistoriqueActionRepository historiqueActionRepository;

    @Autowired
    private UtilisateurRepository utilisateurRepository;

    // Overloaded method for RoleRESTController (no utilisateurId)
    public void saveAction(String actionDescription, Long entityId) {
        saveAction(actionDescription, entityId, null);
    }

    // Original method with utilisateurId
    public void saveAction(String actionDescription, Long entityId, Long utilisateurId) {
        try {
            Utilisateur utilisateur = null;
            if (utilisateurId != null) {
                utilisateur = utilisateurRepository.findById(utilisateurId)
                        .orElseThrow(() -> new IllegalArgumentException("Utilisateur with ID " + utilisateurId + " not found"));
            }

            HistoriqueAction historique = new HistoriqueAction();
            historique.setAction(actionDescription);
            historique.setDate(LocalDateTime.now());
            historique.setUtilisateur(utilisateur);
            historiqueActionRepository.save(historique);
            logger.info("Saved action: {}", actionDescription);
        } catch (Exception e) {
            logger.error("Error saving action: {}", actionDescription, e);
            throw new RuntimeException("Failed to save action", e);
        }
    }

    public List<HistoriqueAction> getAllActions() {
        return historiqueActionRepository.findAll();
    }

    public HistoriqueAction getActionById(Long id) {
        Optional<HistoriqueAction> optional = historiqueActionRepository.findById(id);
        return optional.orElse(null);
    }

    public void deleteActionById(Long id) {
        historiqueActionRepository.deleteById(id);
    }
}