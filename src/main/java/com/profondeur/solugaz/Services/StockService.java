package com.profondeur.solugaz.Services;

import com.profondeur.solugaz.Dto.StockDto;
import com.profondeur.solugaz.Model.Enum.TypeGaz;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface StockService {
    StockDto save(StockDto  dto);
    StockDto findById(Integer id);
    Page<StockDto> findByGazFabricant(Pageable pageable,String fabricant);
    List<StockDto> findStock(int quantite, TypeGaz typeGaz, String fabricant);
    Page<StockDto> findAll(Pageable page);
    void delete(Integer id);
}
