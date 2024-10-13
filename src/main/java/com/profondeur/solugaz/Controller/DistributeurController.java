package com.profondeur.solugaz.Controller;

import com.profondeur.solugaz.Controller.Api.DistributeurApi;
import com.profondeur.solugaz.Dto.DistributeurDto;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DistributeurController implements DistributeurApi {
    @Override
    public ResponseEntity<DistributeurDto> save(DistributeurDto dto) {
        return null;
    }

    @Override
    public DistributeurDto findById(Integer id) {
        return null;
    }

    @Override
    public Page<DistributeurDto> findAll(String sortColumn, int page, int taille, String sortDirection) {
        return null;
    }

    @Override
    public void delete(Integer id) {

    }
}
