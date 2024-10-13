package com.profondeur.solugaz.Repository;

import com.profondeur.solugaz.Model.Gaz;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GazRepository extends JpaRepository<Gaz, Integer> {
}
