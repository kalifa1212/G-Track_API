package com.profondeur.solugaz.Services.Impl;

import com.profondeur.solugaz.Dto.VenteDto;
import com.profondeur.solugaz.Repository.VenteRepository;
import com.profondeur.solugaz.Services.VenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public class VenteServiceImpl implements VenteService {

    @Autowired
    private VenteRepository venteRepository;
    @Autowired
    public VenteServiceImpl(
            VenteRepository venteRepository
    ) {
        this.venteRepository=venteRepository;
    }

    @Override
    public VenteDto save(VenteDto dto) {
        return null;
    }

    @Override
    public VenteDto findById(Integer id) {
        return null;
    }

    @Override
    public Page<VenteDto> findAll(Pageable page) {
        return null;
    }

    @Override
    public void delete(Integer id) {

    }
}
