package com.profondeur.solugaz.Services.Impl;

import com.profondeur.solugaz.Dto.CommandeDto;
import com.profondeur.solugaz.Dto.UtilisateurDto;
import com.profondeur.solugaz.Model.Utilisateur;
import com.profondeur.solugaz.Repository.CommandeRepository;
import com.profondeur.solugaz.Repository.UtilisateurRepository;
import com.profondeur.solugaz.Services.CommandeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
public class CommandeServiceImpl implements CommandeService {

    @Autowired
    private CommandeRepository commandeRepository;
    @Autowired
    private UtilisateurRepository utilisateurRepository;

    @Autowired
    public CommandeServiceImpl(CommandeRepository commandeRepository){
        this.commandeRepository=commandeRepository;
    }
    @Override
    public CommandeDto saveCommande(CommandeDto commandeDto) {
        Optional<Utilisateur> user= utilisateurRepository.findById(commandeDto.getUtilisateur().getId());
        commandeDto.setUtilisateur(UtilisateurDto.fromEntity(user.get()));
        return CommandeDto.fromEntity(commandeRepository.save(CommandeDto.toEntity(commandeDto)));
    }
}
