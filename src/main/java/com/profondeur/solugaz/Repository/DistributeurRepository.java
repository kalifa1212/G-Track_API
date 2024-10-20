package com.profondeur.solugaz.Repository;

import com.profondeur.solugaz.Model.Distributeur;
import com.profondeur.solugaz.Model.Utilisateur;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DistributeurRepository extends JpaRepository<Distributeur, Integer> {
    //@EntityGraph(value = "distributeur.stock")
    Page<Distributeur> findAll(Pageable pageable);
    Page<Distributeur> findAllByLocalisationVille(Pageable pageable,String ville);
    Page<Distributeur> findAllByNomContaining(Pageable pageable,String nom);
}
