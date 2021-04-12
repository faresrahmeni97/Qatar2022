package com.example.tp1.controller;
import com.example.tp1.entities.Equipe;
import com.example.tp1.entities.Joueur;
import com.example.tp1.entities.Staff;
import com.example.tp1.repository.EquipeRepository;
import com.example.tp1.repository.JoueurRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import javax.validation.Valid;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin(origins ="http://localhost:4200")
@RequestMapping("/api")

public class JoueurController {

    private static final Logger logger = LogManager.getLogger(UserController.class);
    @Autowired
    JoueurRepository joueurR;
    @Autowired
    EquipeRepository equipeR;
    private JoueurController FileUploadUtil;
/*
    @PostMapping("/joueurs/save")
    public RedirectView saveUser(Joueur joueur,
                                 @RequestParam("image") MultipartFile multipartFile) throws IOException {

        String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
        joueur.setPhotos(fileName);

        Joueur savedUser = joueurR.save(joueur);

        String uploadDir = "../assets/images/" + savedUser.getId();

        FileUploadUtil.saveFile(uploadDir, fileName, multipartFile);
        // FileUploadUtil.saveFile(uploadDir, fileName, multipartFile);

        return new RedirectView("/joueurs", true);
    }*/
    @PostMapping("/joueur/save")
    public RedirectView saveJoueu(Joueur joueur) throws IOException {

    Joueur joueurDetails = joueurR.save(joueur);

    return new RedirectView("/joueur", true);
    }
    @GetMapping("/joueurs")
    public List<Joueur> getAllJoueurs() {
        List <Joueur> pro = joueurR.findAll();

        for (Joueur joueur : pro) {
            logger.debug("log:     " + joueur);
            System.out.println("sysout:   " + joueur);

        }
        return pro;

    }
    @PostMapping("/addjoueur")
    public Joueur createJoueur(@Valid @RequestBody Joueur joueur) {
        return joueurR.save(joueur);
    }


    @GetMapping("/joueur/{id}")
    public Joueur getJoueurById(@PathVariable(value = "id") Long Id) {
        return joueurR.findById(Id).orElseThrow(null);
        // .orElseThrow(() -> new ResourceNotFoundException("Joueur", "id", Id));
    }

    @DeleteMapping("/joueurdelete/{id}")
    //supprimer joueur
    public ResponseEntity<?> deleteJoueur(@PathVariable(value = "id") Long joueurId) {
        Joueur joueur = joueurR.findById(joueurId).orElseThrow(null);
        joueurR.delete(joueur);
        return ResponseEntity.ok().build();
    }


    @PutMapping("/joueurupdate/{id}/{ideq}")
    public Joueur updateJoueur(@PathVariable(value = "id") Long Id, @PathVariable(value = "ideq") Long Ideq,
                               @Valid @RequestBody Joueur joueurDetails) {
        Equipe equipe =equipeR.findById(Ideq).orElseThrow(null);
        Joueur joueur = joueurR.findById(Id).orElseThrow(null);
        joueur.setEquipe(joueurDetails.getEquipe());
        joueur.setClubjoueur(joueurDetails.getclubjoueur());
        joueur.setNumposte(joueurDetails.getnumposte());
        joueur.setNom(joueurDetails.getNom());
        joueur.setPrenom(joueurDetails.getPrenom());
        joueur.setPoste(joueurDetails.getposte());
        joueur.setPhotos(joueurDetails.getPhotos());
        joueur.setTitulaire(joueurDetails.isTitulaire());
        Joueur updatedJoueur = joueurR.save(joueur);
        return updatedJoueur;
    }

}