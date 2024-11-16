package com.profondeur.solugaz.Repository;

import com.profondeur.solugaz.Model.Stock;
import com.profondeur.solugaz.Model.Enum.TypeGaz;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface StockRepository extends JpaRepository<Stock, Integer> {

    Page<Stock> findAll(Pageable pageable);
    Page<Stock> findAllByGazFabricant(Pageable pageable,String fabricant);
    List<Stock> findStocksByDistributeurId(Integer id);
    @Query("SELECT s FROM Stock s INNER JOIN s.gaz g WHERE s.quantite > :quantite AND g.type = :typeGaz AND g.fabricant = :fabricant")
    List<Stock> findStocks(@Param("quantite") int quantite,@Param("typeGaz") TypeGaz typeGaz,@Param("fabricant") String fabricant);
    List<Stock> findByGazIdAndDistributeurId(Integer gazId, Integer distributeurId);
}
