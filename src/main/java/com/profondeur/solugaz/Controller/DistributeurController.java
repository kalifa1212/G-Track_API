package com.profondeur.solugaz.Controller;

import com.profondeur.solugaz.Controller.Api.DistributeurApi;
import com.profondeur.solugaz.Dto.DistributeurDto;
import com.profondeur.solugaz.Services.DistributeurService;
import com.profondeur.solugaz.Services.LocalisationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DistributeurController implements DistributeurApi {
    private DistributeurService distributeurService;

    @Autowired
    public DistributeurController(
            DistributeurService distributeurService
    ) {
        this.distributeurService = distributeurService;
    }
    @Override
    public ResponseEntity<DistributeurDto> save(DistributeurDto dto) {
        return ResponseEntity.ok(distributeurService.save(dto));
    }

    @Override
    public DistributeurDto findById(Integer id) {
        return distributeurService.findById(id);
    }

    @Override
    public DistributeurDto findByNom(String nom) {
        return null;
    }

    @Override
    public Page<DistributeurDto> findAll(String sortColumn, int page, int taille, String sortDirection) {
        Pageable paging = PageRequest.of(page, taille, Sort.by(sortColumn).ascending());
        return distributeurService.findAll(paging);
    }

    @Override
    public void delete(Integer id) {
        distributeurService.delete(id);
    }
}
