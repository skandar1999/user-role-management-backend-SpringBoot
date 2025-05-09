package com.skandar.gestion.restcontrollers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.skandar.gestion.entities.HistoriqueAction;
import com.skandar.gestion.service.HistoriqueActionService;

@RestController
@RequestMapping("/api/historique")
@CrossOrigin
public class HistoriqueActionController {

    @Autowired
    private HistoriqueActionService historiqueActionService;

    // Endpoint to retrieve all historical actions
    @GetMapping
    public List<HistoriqueAction> getAllActions() {
        return historiqueActionService.getAllActions();
    }

    // Endpoint to get a specific historical action by ID
    @GetMapping("/{id}")
    public HistoriqueAction getActionById(@PathVariable("id") Long id) {
        return historiqueActionService.getActionById(id);
    }

    // Endpoint to delete an action by its ID
    @DeleteMapping("/delete/{id}")
    public void deleteAction(@PathVariable("id") Long id) {
        historiqueActionService.deleteActionById(id);
    }

    // Endpoint to create a new action (you can use this to record user actions)
    @PostMapping("/save")
    public void createAction(@RequestParam("actionDescription") String actionDescription,
                             @RequestParam("utilisateurId") Long utilisateurId) {
        historiqueActionService.saveAction(actionDescription, utilisateurId);
    }
}
