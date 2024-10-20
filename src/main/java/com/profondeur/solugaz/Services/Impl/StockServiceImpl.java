package com.profondeur.solugaz.Services.Impl;

import com.profondeur.solugaz.Dto.StockDto;
import com.profondeur.solugaz.Exceptions.ErrorCodes;
import com.profondeur.solugaz.Exceptions.InvalidEntityException;
import com.profondeur.solugaz.Model.Stock;
import com.profondeur.solugaz.Model.TypeGaz;
import com.profondeur.solugaz.Repository.MouvementRepository;
import com.profondeur.solugaz.Repository.StockRepository;
import com.profondeur.solugaz.Services.StockService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
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
        List<String> errors = new ArrayList<>();
        if (dto.getGaz()==null){
            errors.add("Entrer un gaz");
        }
        if (dto.getQuantite()<0){
            errors.add("Entrer la quantité ou la quantité est inferieur a zero");
        }
        if(!errors.isEmpty()) {
            log.error("stock non valide");
            throw new InvalidEntityException("stock non valide ", ErrorCodes.STOCK_NOT_VALID,errors);
        }
        //if(dto.getId()!=null){return null;}
        return StockDto.fromEntity(stockRepository.save(StockDto.toEntity(dto)));
    }

    @Override
    public StockDto findById(Integer id) {
        return StockDto.fromEntity(stockRepository.findById(id).orElseThrow());
    }

    @Override
    public Page<StockDto> findByGazFabricant(Pageable pageable, String fabricant) {
        return stockRepository.findAllByGazFabricant(pageable, fabricant).map(StockDto::fromEntity);
    }

    @Override
    public List<StockDto> findStock(int quantite, TypeGaz typeGaz, String fabricant) {
        return stockRepository.findStocks(quantite, typeGaz, fabricant).stream().map(StockDto::fromEntity).collect(Collectors.toList());
    }

    @Override
    public Page<StockDto> findAll(Pageable page) {
        return stockRepository.findAll(page).map(StockDto::fromEntity);
    }

    @Override
    public void delete(Integer id) {
        stockRepository.delete(stockRepository.findById(id).orElseThrow());
    }
}
