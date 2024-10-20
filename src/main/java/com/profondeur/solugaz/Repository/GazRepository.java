package com.profondeur.solugaz.Repository;

import com.profondeur.solugaz.Model.Distributeur;
import com.profondeur.solugaz.Model.Gaz;
import com.profondeur.solugaz.Model.TypeGaz;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GazRepository extends JpaRepository<Gaz, Integer> {

    Page<Gaz> findAll(Pageable pageable);
    List<Gaz> findAllByFabricant(String fabricant);
    List<Gaz> findAllByType(TypeGaz typeGaz);
}
