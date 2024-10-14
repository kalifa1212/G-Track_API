package com.profondeur.solugaz.Services.Impl;

import com.profondeur.solugaz.Dto.DistributeurDto;
import com.profondeur.solugaz.Dto.GazDto;
import com.profondeur.solugaz.Exceptions.ErrorCodes;
import com.profondeur.solugaz.Exceptions.InvalidEntityException;
import com.profondeur.solugaz.Repository.DistributeurRepository;
import com.profondeur.solugaz.Repository.GazRepository;
import com.profondeur.solugaz.Repository.RoleRepository;
import com.profondeur.solugaz.Services.GazService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
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
        List<String> errors = new ArrayList<>();
        if (dto.getType()==null){
            errors.add("Entrer un type");
        }
        if (dto.getFabricant()==null){
            errors.add("Entrer le fabricant");
        }
        if(!errors.isEmpty()) {
            log.error("Gaz non valide");
            throw new InvalidEntityException("Gaz non valide ", ErrorCodes.GAZ_NOT_VALID,errors);
        }
        return GazDto.fromEntity(gazRepository.save(GazDto.toEntity(dto)))  ;
    }

    @Override
    public GazDto findById(Integer id) {
        if (id==null){
            return null;
        }
        return GazDto.fromEntity(gazRepository.findById(id).orElseThrow());
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
        return gazRepository.findAll(page).map(GazDto::fromEntity);
    }

    @Override
    public void delete(Integer id) {
        gazRepository.delete(gazRepository.findById(id).orElseThrow());
    }
}
