package com.profondeur.solugaz.Controller;

import com.profondeur.solugaz.Controller.Api.StockApi;
import com.profondeur.solugaz.Dto.StockDto;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StockController implements StockApi {
    @Override
    public ResponseEntity<StockDto> save(StockDto dto) {
        return null;
    }

    @Override
    public StockDto findById(Integer id) {
        return null;
    }

    @Override
    public Page<StockDto> findAll(String sortColumn, int page, int taille, String sortDirection) {
        return null;
    }

    @Override
    public void delete(Integer id) {

    }
}
