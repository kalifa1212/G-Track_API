package com.profondeur.solugaz.Services.Impl;

import com.profondeur.solugaz.Dto.StockDto;
import com.profondeur.solugaz.Repository.MouvementRepository;
import com.profondeur.solugaz.Repository.StockRepository;
import com.profondeur.solugaz.Services.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public class StockServiceImpl implements StockService {

    @Autowired
    private StockRepository stockRepository;
    @Autowired
    public StockServiceImpl(
            StockRepository stockRepository
    ) {
        this.stockRepository=stockRepository;
    }

    @Override
    public StockDto save(StockDto dto) {
        return null;
    }

    @Override
    public StockDto findById(Integer id) {
        return null;
    }

    @Override
    public Page<StockDto> findAll(Pageable page) {
        return null;
    }

    @Override
    public void delete(Integer id) {

    }
}
