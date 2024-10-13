package com.profondeur.solugaz.Services;

import com.profondeur.solugaz.Dto.DistributeurDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface DistributeurService {
    DistributeurDto save(DistributeurDto  dto);
    DistributeurDto findById(Integer id);
    DistributeurDto findByNom(String email);
    DistributeurDto findByPosition(double longitude,double latitude,double altitude);
    Page<DistributeurDto> findAll(Pageable page);
    void delete(Integer id);
}
