package com.profondeur.solugaz.Repository;

import com.profondeur.solugaz.Model.LigneCommande;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LigneCommandeRepository extends JpaRepository<LigneCommande, Integer> {
    List<LigneCommande> findLigneCommandeByCommandeId(Integer idCommande);
}
