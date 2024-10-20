package com.profondeur.solugaz.Services;

import com.profondeur.solugaz.Dto.VenteDto;
import com.profondeur.solugaz.Model.TypeGaz;
import com.profondeur.solugaz.Model.Vente;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface VenteService {
    VenteDto save(VenteDto  dto);
    VenteDto findById(Integer id);
    List<VenteDto> findVenteByGaz(Integer id);
    List<VenteDto> findVenteByDistributeur(Integer id);
    List<VenteDto> findVenteByLocalisationId(Integer id);
    List<VenteDto> findVentesByLocalisationDetail(Integer idLocalisation, TypeGaz typeGaz, String fabricant);
    List<VenteDto> findVentesBydistributeurDetails(Integer idDistributeur,TypeGaz typeGaz,String fabricant);
    Page<VenteDto> findAll(Pageable page);
    void delete(Integer id);
}
