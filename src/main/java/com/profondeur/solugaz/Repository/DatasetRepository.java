package com.profondeur.solugaz.Repository;

import com.profondeur.solugaz.Model.Dataset;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DatasetRepository extends JpaRepository<Dataset, Integer> {
}
