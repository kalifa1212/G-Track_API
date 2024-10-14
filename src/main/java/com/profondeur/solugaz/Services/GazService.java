package com.profondeur.solugaz.Services;

import com.profondeur.solugaz.Dto.DistributeurDto;
import com.profondeur.solugaz.Dto.GazDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface GazService {
    GazDto save(GazDto  dto);
    GazDto findById(Integer id);
    GazDto findByType(String email);
    GazDto findByDistributeur(DistributeurDto dto);
    Page<GazDto> findAll(Pageable page);
    void delete(Integer id);
}
