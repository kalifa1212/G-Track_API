package com.profondeur.solugaz.Services;

import com.profondeur.solugaz.Dto.MouvementDto;
import com.profondeur.solugaz.Model.Mouvement;
import com.profondeur.solugaz.Model.TypeMouvement;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface MouvementService {
    MouvementDto save(MouvementDto  dto);
    MouvementDto findById(Integer id);
    List<MouvementDto> findBytypeMouvement(TypeMouvement typeMouvement);
    Page<MouvementDto> findAll(Pageable page);
    void delete(Integer id);
}
