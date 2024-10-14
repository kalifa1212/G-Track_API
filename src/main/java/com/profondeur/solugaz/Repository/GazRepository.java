package com.profondeur.solugaz.Repository;

import com.profondeur.solugaz.Model.Distributeur;
import com.profondeur.solugaz.Model.Gaz;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GazRepository extends JpaRepository<Gaz, Integer> {

    Page<Gaz> findAll(Pageable pageable);

}
