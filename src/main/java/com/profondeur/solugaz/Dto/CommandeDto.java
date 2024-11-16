package com.profondeur.solugaz.Dto;

import com.profondeur.solugaz.Model.Commande;
import com.profondeur.solugaz.Model.Enum.CommandeStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CommandeDto {

    private Integer id;
    private String reference;
    private LocalDateTime dateCommande;
    private double montant;
    private CommandeStatus status;
    private UtilisateurDto utilisateur;
    private List<LigneCommandeDto> ligneCommande;

    public static CommandeDto fromEntity(Commande commande) {
        if(commande==null){
            return null;
        }
        return CommandeDto.builder()
                .id(commande.getId())
                .dateCommande(commande.getDateCommande())
                .montant(commande.getMontant())
                .status(commande.getStatus())
                .reference(commande.getReference())
                .utilisateur(UtilisateurDto.fromEntity(commande.getUtilisateur()))
                //.ligneCommande(commande.getLigneCommande().stream().map(LigneCommandeDto::fromEntity).collect(Collectors.toList()))
                .build();
    }
    public static Commande toEntity(CommandeDto commandeDto) {
        if(commandeDto==null) {
            return null;
        }
        Commande commande = new Commande();
        commande.setDateCommande(commandeDto.getDateCommande());
        //TODO A revoir
        //commande.setLigneCommande(commandeDto.getLigneCommande().stream().map(LigneCommandeDto::toEntity).collect(Collectors.toList()));
        commande.setMontant(commandeDto.getMontant());
        commande.setStatus(commandeDto.getStatus());
        commande.setUtilisateur(UtilisateurDto.toEntity(commandeDto.getUtilisateur()));
        commande.setId(commandeDto.getId());
        commande.setReference(commandeDto.getReference());
        return commande;
    }
}
