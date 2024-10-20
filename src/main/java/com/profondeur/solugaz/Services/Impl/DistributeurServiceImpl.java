package com.profondeur.solugaz.Services.Impl;

import com.profondeur.solugaz.Dto.DistributeurDto;
import com.profondeur.solugaz.Dto.StockDto;
import com.profondeur.solugaz.Dto.UtilisateurDto;
import com.profondeur.solugaz.Exceptions.EntityNotFoundException;
import com.profondeur.solugaz.Exceptions.ErrorCodes;
import com.profondeur.solugaz.Exceptions.InvalidEntityException;
import com.profondeur.solugaz.Model.Distributeur;
import com.profondeur.solugaz.Repository.DistributeurRepository;
import com.profondeur.solugaz.Services.DistributeurService;
import com.profondeur.solugaz.Services.StockService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class DistributeurServiceImpl implements DistributeurService {

    @Autowired
    private DistributeurRepository distributeurRepository;
    private StockService stockService;
    @Autowired
    public DistributeurServiceImpl(
            DistributeurRepository distributeurRepository,
            StockService stockService
    ) {
        this.distributeurRepository=distributeurRepository;
        this.stockService=stockService;
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
    public Page<DistributeurDto> findByNom(Pageable pageable, String nom) {

        return distributeurRepository.findAllByNomContaining(pageable,nom).map(DistributeurDto::fromEntity);
    }

    @Override
    public Page<DistributeurDto> findByville(Pageable pageable, String ville) {
        return distributeurRepository.findAllByLocalisationVille(pageable,ville).map(DistributeurDto::fromEntity);
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
