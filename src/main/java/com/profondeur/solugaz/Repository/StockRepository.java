package com.profondeur.solugaz.Repository;

import com.profondeur.solugaz.Model.Distributeur;
import com.profondeur.solugaz.Model.Stock;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StockRepository extends JpaRepository<Stock, Integer> {

    Page<Stock> findAll(Pageable pageable);

}
