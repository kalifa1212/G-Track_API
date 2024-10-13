package com.profondeur.solugaz.Services;

import com.profondeur.solugaz.Dto.MouvementDto;
import com.profondeur.solugaz.Model.Mouvement;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface MouvementService {
    MouvementDto save(MouvementDto  dto);
    MouvementDto findById(Integer id);
    Page<MouvementDto> findAll(Pageable page);
    void delete(Integer id);
}
