package com.profondeur.solugaz.Services;

import com.profondeur.solugaz.Dto.StockDto;
import com.profondeur.solugaz.Model.Stock;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface StockService {
    StockDto save(StockDto  dto);
    StockDto findById(Integer id);
    Page<StockDto> findAll(Pageable page);
    void delete(Integer id);
}
