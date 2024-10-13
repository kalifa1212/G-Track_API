package com.profondeur.solugaz.Services;

import com.profondeur.solugaz.Dto.VenteDto;
import com.profondeur.solugaz.Model.Vente;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface VenteService {
    VenteDto save(VenteDto  dto);
    VenteDto findById(Integer id);
    Page<VenteDto> findAll(Pageable page);
    void delete(Integer id);
}
