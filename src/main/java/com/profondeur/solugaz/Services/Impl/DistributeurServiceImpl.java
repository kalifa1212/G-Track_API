package com.profondeur.solugaz.Services.Impl;

import com.profondeur.solugaz.Dto.DistributeurDto;
import com.profondeur.solugaz.Repository.DistributeurRepository;
import com.profondeur.solugaz.Services.DistributeurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public class DistributeurServiceImpl implements DistributeurService {

    @Autowired
    private DistributeurRepository distributeurRepository;
    @Autowired
    public DistributeurServiceImpl(
            DistributeurRepository distributeurRepository
    ) {
        this.distributeurRepository=distributeurRepository;
    }

    @Override
    public DistributeurDto save(DistributeurDto dto) {
        return null;
    }

    @Override
    public DistributeurDto findById(Integer id) {
        return null;
    }

    @Override
    public DistributeurDto findByNom(String email) {
        return null;
    }

    @Override
    public DistributeurDto findByPosition(double longitude, double latitude, double altitude) {
        return null;
    }

    @Override
    public Page<DistributeurDto> findAll(Pageable page) {
        return null;
    }

    @Override
    public void delete(Integer id) {

    }
}
