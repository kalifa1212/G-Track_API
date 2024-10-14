package com.profondeur.solugaz.Controller;

import com.profondeur.solugaz.Controller.Api.VenteApi;
import com.profondeur.solugaz.Dto.VenteDto;
import com.profondeur.solugaz.Services.DistributeurService;
import com.profondeur.solugaz.Services.VenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VenteController implements VenteApi {

    private VenteService venteService;


    @Autowired
    public VenteController(
            VenteService venteService
    ) {
        this.venteService = venteService;
    }
    @Override
    public ResponseEntity<VenteDto> save(VenteDto dto) {
        return ResponseEntity.ok(venteService.save(dto));
    }

    @Override
    public VenteDto findById(Integer id) {
        return venteService.findById(id);
    }

    @Override
    public Page<VenteDto> findAll(String sortColumn, int page, int taille, String sortDirection) {
        Pageable paging = PageRequest.of(page, taille, Sort.by(sortColumn).ascending());

        return venteService.findAll(paging);
    }

    @Override
    public void delete(Integer id) {
        venteService.delete(id);
    }
}
