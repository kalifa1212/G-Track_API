package com.profondeur.solugaz.Repository;

import com.profondeur.solugaz.Model.Mouvement;
import com.profondeur.solugaz.Model.TypeMouvement;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MouvementRepository extends JpaRepository<Mouvement, Integer> {

    List<Mouvement> findAllByTypeMouvement(TypeMouvement typeMouvement);
}
