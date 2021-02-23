package com.example.tp1.entities;


import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Equipe implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;
    private String Paysequipe;
    private String formation;
    private Long nbcoupe;
    @Column(nullable = true, length = 64)
    private String imageequipe;

    @OneToMany(mappedBy="equipe", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Joueur> joueurs=new ArrayList<>();


    @JsonIgnore

    //@JsonManagedReference//@JsonBackReference

    public Equipe() {
        super();
    }
    public Long getId() {
        return id;
    }


    public Equipe(Long id, String Paysequipe, String Systemequipenationnal, Long nbcoupe,String imageequipe) {
        super();
        this.id = id;
        this.Paysequipe = Paysequipe;
        this.formation = Systemequipenationnal;
        this.nbcoupe=nbcoupe;
        this.imageequipe=imageequipe;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public String getPaysequipe() {
        return Paysequipe;
    }
    public String getSystemequipenationnal() {
        return formation;
    }
    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Long getNbcoupe() {
        return nbcoupe;
    }


    public void setPaysequipe(String paysequipe) {
        Paysequipe = paysequipe;
    }

    public void setSystemequipenationnal(String systemequipenationnal) {
        formation = systemequipenationnal;
    }

    public void setNbcoupe(Long nbcoupe) {
        this.nbcoupe = nbcoupe;
    }

    public void setImageequipe(String imageequipe) {
        this.imageequipe = imageequipe;
    }

    public void setPhotos(String imageequipe) {this.imageequipe = imageequipe;}
    public String getImageequipe() {
        if (imageequipe == null || id == null) return null;

        return "/equipes-photos/" + id + "/" + imageequipe;
    }
    @Override
    public String toString() {
        return "Equipe [id=" + id + ", Paysequipe=" + Paysequipe + ", Systemequipenationnal=" + formation  +", nbcoupe=" + nbcoupe + ", imageequipe=" + imageequipe + "]";
    }

    public List<Joueur> getJoueurs() {
        return joueurs;
    }

    public void setJoueurs(List<Joueur> joueurs) {
        this.joueurs = joueurs;
    }
}
