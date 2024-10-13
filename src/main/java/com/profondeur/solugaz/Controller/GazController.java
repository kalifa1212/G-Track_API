package com.profondeur.solugaz.Controller;

import com.profondeur.solugaz.Controller.Api.GazApi;
import com.profondeur.solugaz.Dto.GazDto;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GazController implements GazApi {
    @Override
    public ResponseEntity<GazDto> save(GazDto dto) {
        return null;
    }

    @Override
    public GazDto findById(Integer id) {
        return null;
    }

    @Override
    public Page<GazDto> findAll(String sortColumn, int page, int taille, String sortDirection) {
        return null;
    }

    @Override
    public void delete(Integer id) {

    }
}
