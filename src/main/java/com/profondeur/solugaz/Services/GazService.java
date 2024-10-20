package com.profondeur.solugaz.Services;

import com.profondeur.solugaz.Dto.DistributeurDto;
import com.profondeur.solugaz.Dto.GazDto;
import com.profondeur.solugaz.Model.Gaz;
import com.profondeur.solugaz.Model.TypeGaz;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface GazService {
    GazDto save(GazDto  dto);
    GazDto findById(Integer id);
    List<GazDto> findByType(TypeGaz type);
    List<GazDto> findByFabricant(String fabricant);
    Page<GazDto> findAll(Pageable page);
    void delete(Integer id);
}
