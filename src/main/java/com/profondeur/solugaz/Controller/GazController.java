package com.profondeur.solugaz.Controller;

import com.profondeur.solugaz.Controller.Api.GazApi;
import com.profondeur.solugaz.Dto.GazDto;
import com.profondeur.solugaz.Model.Enum.Fabricant;
import com.profondeur.solugaz.Model.Enum.TypeGaz;
import com.profondeur.solugaz.Services.GazService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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
    public List<GazDto> findByType(TypeGaz type) {
        return gazService.findByType(type);
    }

    @Override
    public List<GazDto> findByFabricant(String fabricant) {
        if (fabricant.equals(Fabricant.CAMGAZ))
        {
            return gazService.findByFabricant(Fabricant.CAMGAZ);
        }
        if (fabricant.equals(Fabricant.TOTAL))
        {
            return gazService.findByFabricant(Fabricant.TOTAL);
        }
        if (fabricant.equals(Fabricant.TRADEX))
        {
            return gazService.findByFabricant(Fabricant.TRADEX);
        }
        return null;
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
