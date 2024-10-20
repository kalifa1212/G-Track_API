package com.profondeur.solugaz.Controller;

import com.profondeur.solugaz.Controller.Api.StockApi;
import com.profondeur.solugaz.Dto.StockDto;
import com.profondeur.solugaz.Model.TypeGaz;
import com.profondeur.solugaz.Services.DistributeurService;
import com.profondeur.solugaz.Services.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class StockController implements StockApi {
    private StockService stockService;


    @Autowired
    public StockController(
            StockService stockService
    ) {
        this.stockService = stockService;
    }
    @Override
    public ResponseEntity<StockDto> save(StockDto dto) {
        return ResponseEntity.ok(stockService.save(dto));
    }

    @Override
    public StockDto findById(Integer id) {
        return stockService.findById(id);
    }

    @Override
    public Page<StockDto> findAll(String sortColumn, int page, int taille, String sortDirection) {
        Pageable paging = PageRequest.of(page, taille, Sort.by(sortColumn).ascending());

        return stockService.findAll(paging);
    }

    @Override
    public Page<StockDto> findByGazFabricant(String sortColumn, int page, int taille, String sortDirection, String fabricant) {
        Pageable paging = PageRequest.of(page, taille, Sort.by(sortColumn).ascending());

        return stockService.findByGazFabricant(paging,fabricant);
    }

    @Override
    public List<StockDto> findStock(int quantite, TypeGaz typeGaz, String fabricant) {
        return stockService.findStock(quantite,typeGaz,fabricant);
    }

    @Override
    public void delete(Integer id) {
        stockService.delete(id);
    }
}
