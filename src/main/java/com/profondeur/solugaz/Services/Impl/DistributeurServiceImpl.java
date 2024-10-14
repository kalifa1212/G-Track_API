package com.profondeur.solugaz.Services.Impl;

import com.profondeur.solugaz.Dto.DistributeurDto;
import com.profondeur.solugaz.Dto.UtilisateurDto;
import com.profondeur.solugaz.Exceptions.EntityNotFoundException;
import com.profondeur.solugaz.Exceptions.ErrorCodes;
import com.profondeur.solugaz.Exceptions.InvalidEntityException;
import com.profondeur.solugaz.Model.Distributeur;
import com.profondeur.solugaz.Repository.DistributeurRepository;
import com.profondeur.solugaz.Services.DistributeurService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
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
        List<String> errors = new ArrayList<>();
        if (dto.getNom().isEmpty()){
            errors.add("Entrer un nom");
        }
        if (dto.getLocalisation()==null){
            errors.add("Entrer une localisation");
        }
        if(!errors.isEmpty()) {
            log.error("Distributeur non valide");
            throw new InvalidEntityException("Distributeur non valide ", ErrorCodes.DISTRIBUTEUR_NOT_VALID,errors);
        }

        return DistributeurDto.fromEntity(distributeurRepository.save(DistributeurDto.toEntity(dto)));
    }

    @Override
    public DistributeurDto findById(Integer id) {
        if (id==null){
            return null;
        }
        return distributeurRepository.findById(id)
                .map(DistributeurDto::fromEntity)
                .orElseThrow(()-> new EntityNotFoundException("Aucun Distributeur trouver", ErrorCodes.DISTRIBUTEUR_NOT_FOUND)
                );
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
        return distributeurRepository.findAll(page).map(DistributeurDto::fromEntity);
    }

    @Override
    public void delete(Integer id) {
        Distributeur dist= distributeurRepository.findById(id).orElseThrow();
        distributeurRepository.delete(dist);
    }
}
