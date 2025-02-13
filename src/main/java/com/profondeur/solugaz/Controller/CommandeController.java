package com.profondeur.solugaz.Controller;

import com.profondeur.solugaz.Controller.Api.CommandeApi;
import com.profondeur.solugaz.Dto.CommandeDto;
import com.profondeur.solugaz.Dto.LigneCommandeDto;
import com.profondeur.solugaz.Repository.CommandeRepository;
import com.profondeur.solugaz.Repository.LigneCommandeRepository;
import com.profondeur.solugaz.Services.CommandeService;
import com.profondeur.solugaz.Services.LigneCommandeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CommandeController implements CommandeApi {

    private CommandeService commandeService;
    private LigneCommandeService ligneCommandeService;

    @Autowired
    public CommandeController(CommandeService commandeService,LigneCommandeService ligneCommandeService){
        this.ligneCommandeService=ligneCommandeService;
        this.commandeService=commandeService;
    }
    @Override
    public ResponseEntity<CommandeDto> saveCommande(CommandeDto dto) {
        return ResponseEntity.ok(commandeService.saveCommande(dto));
    }

    @Override
    public ResponseEntity<LigneCommandeDto> saveLigneCommande(LigneCommandeDto dto) {
        return ResponseEntity.ok(ligneCommandeService.nouvelleCommande(dto));
    }
}
