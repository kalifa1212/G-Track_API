package com.profondeur.solugaz.Services.Impl;

import com.profondeur.solugaz.Dto.MouvementDto;
import com.profondeur.solugaz.Model.TypeMouvement;
import com.profondeur.solugaz.Repository.MouvementRepository;
import com.profondeur.solugaz.Services.MouvementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MouvementServiceImpl implements MouvementService {

    @Autowired
    private MouvementRepository mouvementRepository;
    @Autowired
    public MouvementServiceImpl(
            MouvementRepository mouvementRepository
    ) {
        this.mouvementRepository=mouvementRepository;
    }
    @Override
    public MouvementDto save(MouvementDto dto) {

        return MouvementDto.fromEntity(mouvementRepository.save(MouvementDto.toEntity(dto)));
    }

    @Override
    public MouvementDto findById(Integer id) {
        return MouvementDto.fromEntity(mouvementRepository.findById(id).orElseThrow());
    }

    @Override
    public List<MouvementDto> findBytypeMouvement(TypeMouvement typeMouvement) {
        return mouvementRepository.findAllByTypeMouvement(typeMouvement).stream().map(MouvementDto::fromEntity).collect(Collectors.toList());
    }

    @Override
    public Page<MouvementDto> findAll(Pageable page) {
        return mouvementRepository.findAll(page).map(MouvementDto::fromEntity);
    }

    @Override
    public void delete(Integer id) {
        mouvementRepository.delete(mouvementRepository.findById(id).orElseThrow());
    }
}
