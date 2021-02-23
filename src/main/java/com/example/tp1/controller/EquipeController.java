package com.example.tp1.controller;

import com.example.tp1.entities.Equipe;
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
        List <Equipe> pro = equipr.findAll();

        for (Equipe equipe : pro) {
            logger.debug("log:     " + equipe);
            System.out.println("sysout:   " + equipe);

        }
        return pro;

    }
    @PostMapping("/addequipe")
    public Equipe createEquipe(@Valid @RequestBody Equipe equipe) {
        return equipr.save(equipe);
    }




    @DeleteMapping("/equiped/{id}")
    public ResponseEntity<?> deleteEquipe(@PathVariable(value = "id") Long equipeId) {
        Equipe equipe = equipr.findById(equipeId).orElseThrow(null);
        //.orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));

        // userRepository.deleteById(userId);
        equipr.delete(equipe);

        return ResponseEntity.ok().build();
    }


    @PutMapping("/equipe/{id}")
    public Equipe updateEquipe(@PathVariable(value = "id") Long Id,
                               @Valid @RequestBody Equipe equipeDetails) {

        Equipe equipe = equipr.findById(Id).orElseThrow(null);


        equipe.setImageequipe(equipeDetails.getImageequipe());
        equipe.setNbcoupe(equipeDetails.getNbcoupe());
        equipe.setPaysequipe(equipeDetails.getPaysequipe());
        equipe.setSystemequipenationnal(equipeDetails.getSystemequipenationnal());


        Equipe updatedEquipe = equipr.save(equipe);
        return updatedEquipe;
    }
}
