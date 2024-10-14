package com.profondeur.solugaz.Services.Impl;

import com.profondeur.solugaz.Dto.VenteDto;
import com.profondeur.solugaz.Exceptions.ErrorCodes;
import com.profondeur.solugaz.Exceptions.InvalidEntityException;
import com.profondeur.solugaz.Repository.VenteRepository;
import com.profondeur.solugaz.Services.VenteService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
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
        List<String> errors = new ArrayList<>();
        if (dto.getQuantite()==0){
            errors.add("Entrer la quantité");
        }
        if (dto.getGaz()==null){
            errors.add("Entrer le gaz");
        }
        if(!errors.isEmpty()) {
            log.error("Vente non valide");
            throw new InvalidEntityException("Vente non valide ", ErrorCodes.VENTE_NOT_VALID,errors);
        }
        return VenteDto.fromEntity(venteRepository.save(VenteDto.toEntity(dto)));
    }

    @Override
    public VenteDto findById(Integer id) {
        return VenteDto.fromEntity(venteRepository.findById(id).orElseThrow());
    }

    @Override
    public Page<VenteDto> findAll(Pageable page) {
        return venteRepository.findAll(page).map(VenteDto::fromEntity);
    }

    @Override
    public void delete(Integer id) {
        venteRepository.delete(venteRepository.findById(id).orElseThrow());
    }
}
