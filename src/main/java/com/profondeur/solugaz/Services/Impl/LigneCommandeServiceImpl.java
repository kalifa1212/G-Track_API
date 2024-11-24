package com.profondeur.solugaz.Services.Impl;

import com.profondeur.solugaz.Dto.LigneCommandeDto;
import com.profondeur.solugaz.Repository.LigneCommandeRepository;
import com.profondeur.solugaz.Services.LigneCommandeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class LigneCommandeServiceImpl implements LigneCommandeService {

    @Autowired
    private LigneCommandeRepository ligneCommandeRepository;
    @Autowired
    public LigneCommandeServiceImpl(LigneCommandeRepository ligneCommandeRepository){
         this.ligneCommandeRepository=ligneCommandeRepository;
    }
    @Override
    public LigneCommandeDto nouvelleCommande(LigneCommandeDto ligneCommandeDto) {
        return LigneCommandeDto.fromEntity(ligneCommandeRepository.save(LigneCommandeDto.toEntity(ligneCommandeDto)));
    }
}
