package com.profondeur.solugaz.Controller;

import com.profondeur.solugaz.Controller.Api.GazApi;
import com.profondeur.solugaz.Dto.GazDto;
import com.profondeur.solugaz.Services.DistributeurService;
import com.profondeur.solugaz.Services.GazService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GazController implements GazApi {

    private GazService gazService;


    @Autowired
    public GazController(
            GazService gazService
    ) {
        this.gazService = gazService;
    }
    @Override
    public ResponseEntity<GazDto> save(GazDto dto) {
        return ResponseEntity.ok(gazService.save(dto));
    }

    @Override
    public GazDto findById(Integer id) {
        return gazService.findById(id);
    }

    @Override
    public Page<GazDto> findAll(String sortColumn, int page, int taille, String sortDirection) {
        Pageable paging = PageRequest.of(page, taille, Sort.by(sortColumn).ascending());
        return gazService.findAll(paging);
    }

    @Override
    public void delete(Integer id) {
        gazService.delete(id);
    }
}
