package com.profondeur.solugaz.Controller;

import com.profondeur.solugaz.Controller.Api.CommandeApi;
import com.profondeur.solugaz.Dto.CommandeDto;
import com.profondeur.solugaz.Repository.CommandeRepository;
import com.profondeur.solugaz.Repository.LigneCommandeRepository;
import com.profondeur.solugaz.Services.CommandeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CommandeController implements CommandeApi {

    private CommandeRepository commandeRepository;
    private LigneCommandeRepository ligneCommandeRepository;

    @Autowired
    public CommandeController(CommandeRepository commandeRepository,LigneCommandeRepository ligneCommandeRepository){
        this.commandeRepository=commandeRepository;
        this.ligneCommandeRepository=ligneCommandeRepository;
    }
    @Override
    public ResponseEntity<CommandeDto> saveCommande(CommandeDto dto) {
        return null;
    }

    @Override
    public ResponseEntity<CommandeDto> saveLigneCommande(CommandeDto dto) {
        return null;
    }
}
