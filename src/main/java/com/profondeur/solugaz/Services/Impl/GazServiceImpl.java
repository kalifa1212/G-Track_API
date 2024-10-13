package com.profondeur.solugaz.Services.Impl;

import com.profondeur.solugaz.Dto.DistributeurDto;
import com.profondeur.solugaz.Dto.GazDto;
import com.profondeur.solugaz.Repository.DistributeurRepository;
import com.profondeur.solugaz.Repository.GazRepository;
import com.profondeur.solugaz.Repository.RoleRepository;
import com.profondeur.solugaz.Services.GazService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public class GazServiceImpl implements GazService {

    @Autowired
    private GazRepository gazRepository;
    @Autowired
    public GazServiceImpl(
            GazRepository gazRepository
    ) {
        this.gazRepository=gazRepository;
    }

    @Override
    public GazDto save(GazDto dto) {
        return null;
    }

    @Override
    public GazDto findById(Integer id) {
        return null;
    }

    @Override
    public GazDto findByType(String email) {
        return null;
    }

    @Override
    public GazDto findByDistributeur(DistributeurDto dto) {
        return null;
    }

    @Override
    public Page<GazDto> findAll(Pageable page) {
        return null;
    }
}
