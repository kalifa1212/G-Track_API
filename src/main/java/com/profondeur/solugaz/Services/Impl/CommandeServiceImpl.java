package com.profondeur.solugaz.Services.Impl;

import com.profondeur.solugaz.Dto.CommandeDto;
import com.profondeur.solugaz.Repository.CommandeRepository;
import com.profondeur.solugaz.Repository.LigneCommandeRepository;
import com.profondeur.solugaz.Services.CommandeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class CommandeServiceImpl implements CommandeService {

    @Autowired
    private CommandeRepository commandeRepository;

    @Autowired
    public CommandeServiceImpl(CommandeRepository commandeRepository){
        this.commandeRepository=commandeRepository;
    }
    @Override
    public CommandeDto saveCommande(CommandeDto commandeDto) {
        return CommandeDto.fromEntity(commandeRepository.save(CommandeDto.toEntity(commandeDto)));
    }
}
