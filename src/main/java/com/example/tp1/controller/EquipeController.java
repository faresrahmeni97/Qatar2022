package com.example.tp1.controller;

import com.example.tp1.entities.Equipe;
import com.example.tp1.entities.Joueur;
import com.example.tp1.repository.EquipeRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@CrossOrigin(origins ="http://localhost:4200")

@RequestMapping("/api")

public class EquipeController {

    private static final Logger logger = LogManager.getLogger(EquipeController.class);
    @Autowired

    EquipeRepository equipr;


    @GetMapping("/equipes")

    public List<Equipe> getAllEquipes() {
        List <Equipe> equipes = equipr.findAll();
        return equipes;

    }
    @PostMapping("/equipeadd")
    public Equipe createEquipe(@Valid @RequestBody Equipe equipe) {
        return equipr.save(equipe);
    }




    @DeleteMapping("/equipedelete/{id}")
    public ResponseEntity<?> deleteEquipe(@PathVariable(value = "id") Long equipeId) {
        Equipe equipe = equipr.findById(equipeId).orElseThrow(null);
        //.orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));

        // userRepository.deleteById(userId);
        equipr.delete(equipe);

        return ResponseEntity.ok().build();
    }

    @GetMapping("/equipe/{id}")
    public Equipe getEquipeById(@PathVariable(value = "id") Long Id) {
        return equipr.findById(Id).orElseThrow(null);

    }

    @PutMapping("/equipeupdate/{id}")
    public Equipe updateEquipe(@PathVariable(value = "id") Long Id,
                               @Valid @RequestBody Equipe equipeDetails) {

        Equipe equipe = equipr.findById(Id).orElseThrow(null);
        equipe.setImageequipe(equipeDetails.getImageequipe());
        equipe.setNbcoupe(equipeDetails.getNbcoupe());
        equipe.setPaysequipe(equipeDetails.getPaysequipe());
        equipe.setFormation(equipeDetails.getFormation());


        Equipe updatedEquipe = equipr.save(equipe);
        return updatedEquipe;
    }
}
