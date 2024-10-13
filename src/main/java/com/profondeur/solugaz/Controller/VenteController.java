package com.profondeur.solugaz.Controller;

import com.profondeur.solugaz.Controller.Api.VenteApi;
import com.profondeur.solugaz.Dto.VenteDto;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VenteController implements VenteApi {
    @Override
    public ResponseEntity<VenteDto> save(VenteDto dto) {
        return null;
    }

    @Override
    public VenteDto findById(Integer id) {
        return null;
    }

    @Override
    public Page<VenteDto> findAll(String sortColumn, int page, int taille, String sortDirection) {
        return null;
    }

    @Override
    public void delete(Integer id) {

    }
}
