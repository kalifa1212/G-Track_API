package com.profondeur.solugaz.Services;

import com.profondeur.solugaz.Dto.CommandeDto;
import com.profondeur.solugaz.Model.Commande;

public interface CommandeService {
    CommandeDto saveCommande(CommandeDto commandeDto);
}
