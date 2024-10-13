package com.profondeur.solugaz.Repository;

import com.profondeur.solugaz.Model.Stock;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StockRepository extends JpaRepository<Stock, Integer> {
}
