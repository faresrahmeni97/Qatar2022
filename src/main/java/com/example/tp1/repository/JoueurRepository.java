package com.example.tp1.repository;


import com.example.tp1.entities.Joueur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JoueurRepository extends JpaRepository<Joueur,Long> {

     //   Optional<Equipe> findByIdAndInstructorId(Long id, Long equipeId);

}
