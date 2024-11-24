package com.profondeur.solugaz.Repository;

import com.profondeur.solugaz.Model.Commande;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommandeRepository extends JpaRepository<Commande, Integer> {
}
