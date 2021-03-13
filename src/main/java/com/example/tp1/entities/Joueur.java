package com.example.tp1.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Joueur implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;
    private String nom;
    private String prenom;
    private String clubjoueur;
    private Long numposte;
    private String poste;

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }
    private boolean titulaire;
    private String photoJoueur;

    @ManyToOne
    private Equipe equipe;

    public void setEquipe(Equipe equipe) {
        this.equipe = equipe;
    }

//@ManyToMany(mappedBy="joueurs",cascade=CascadeType.ALL,fetch = FetchType.LAZY)


    //@JsonManagedReference//@JsonBackReference



    public Joueur() {
        super();
    }

    public Equipe getEquipe() {
        return equipe;
    }
    public Long getId() {
        return id;
    }




    public Joueur(Long id, String clubjoueur, Long numposte, String poste,String photos) {
        super();
        this.id = id;
        this.clubjoueur = clubjoueur;
        this.numposte = numposte;
        this.poste = poste;
        this.photoJoueur=photos;

    }
    public void setId(Long id) {
        this.id = id;
    }

    public String getclubjoueur() {
        return clubjoueur;
    }
    public void setClubjoueur(String clubjoueur) {
        this.clubjoueur = clubjoueur;
    }

    public Long getnumposte() {
        return numposte;
    }
    public void setNumposte(Long numposte) {
        this.numposte = numposte;
    }

    public String getposte() { return poste; }
    public void setPoste(String poste) {
        this.poste = poste;
    }

    public boolean isTitulaire() {
        return titulaire;
    }

    public void setTitulaire(boolean titulaire) {
        this.titulaire = titulaire;
    }

    public void setPhotos(String photos) {this.photoJoueur = photos;}
    public String getPhotos() {
        if (photoJoueur == null || id == null) return null;

        return "/joueur-photos/" + id + "/" + photoJoueur;
    }
    @Override
    public String toString() {
        return "Joueur [id=" + id + ", clubjoueur=" + clubjoueur + ", numposte=" + numposte + ", poste=" + poste +", photos=" + photoJoueur + "]";
    }


}
