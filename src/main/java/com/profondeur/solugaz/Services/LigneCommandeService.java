package com.profondeur.solugaz.Services;

import com.profondeur.solugaz.Dto.LigneCommandeDto;
import com.profondeur.solugaz.Repository.LigneCommandeRepository;
import org.springframework.beans.factory.annotation.Autowired;


public interface LigneCommandeService {
    LigneCommandeDto nouvelleCommande(LigneCommandeDto ligneCommandeDto);
}
