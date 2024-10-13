package com.profondeur.solugaz.Repository;

import com.profondeur.solugaz.Model.Mouvement;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MouvementRepository extends JpaRepository<Mouvement, Integer> {
}
