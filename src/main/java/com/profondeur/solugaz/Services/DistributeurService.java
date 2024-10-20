package com.profondeur.solugaz.Services;

import com.profondeur.solugaz.Dto.DistributeurDto;
import com.profondeur.solugaz.Model.Distributeur;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface DistributeurService {
    DistributeurDto save(DistributeurDto  dto);
    DistributeurDto findById(Integer id);
    Page<DistributeurDto> findByNom(Pageable pageable,String nom);
    Page<DistributeurDto> findByville(Pageable pageable,String ville);
    DistributeurDto findByPosition(double longitude,double latitude,double altitude);
    Page<DistributeurDto> findAll(Pageable page);
    void delete(Integer id);
}
