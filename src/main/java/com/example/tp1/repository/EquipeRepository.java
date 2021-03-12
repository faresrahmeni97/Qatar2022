package com.example.tp1.repository;


import com.example.tp1.entities.Equipe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EquipeRepository extends JpaRepository<Equipe,Long> {

   // List<Equipe> findByEquipeId(Long equipeId);
}
