package com.profondeur.solugaz.Dto;

import com.profondeur.solugaz.Model.Enum.Fabricant;
import com.profondeur.solugaz.Model.Enum.TypeGaz;
import com.profondeur.solugaz.Model.LigneCommande;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LigneCommandeDto {

    private Integer id;
    private CommandeDto commande;
    private Integer quantite;
    private GazDto gaz;
    private double prix_unitaire;
    private DistributeurDto distributeur;

    public static LigneCommandeDto fromEntity(LigneCommande ligneCommande) {
       if(ligneCommande==null){
           return null;
       }
       return LigneCommandeDto.builder()
               .id(ligneCommande.getId())
               .commande(CommandeDto.fromEntity(ligneCommande.getCommande()))
               .quantite(ligneCommande.getQuantite())
               .distributeur(DistributeurDto.fromEntity(ligneCommande.getDistributeur()))
               .prix_unitaire(ligneCommande.getPrix_unitaire())
               .gaz(GazDto.fromEntity(ligneCommande.getGaz()))
               .build();
    }
    public static LigneCommande toEntity(LigneCommandeDto ligneCommandeDto) {
        if(ligneCommandeDto==null){
            return null;
        }
        LigneCommande ligneCommande= new LigneCommande();
        ligneCommande.setCommande(CommandeDto.toEntity(ligneCommandeDto.getCommande()));
        ligneCommande.setDistributeur(DistributeurDto.toEntity(ligneCommandeDto.getDistributeur()));
        ligneCommande.setQuantite(ligneCommandeDto.getQuantite());
        ligneCommande.setPrix_unitaire(ligneCommandeDto.getPrix_unitaire());
        ligneCommande.setId(ligneCommandeDto.getId());
        ligneCommande.setGaz(GazDto.toEntity(ligneCommandeDto.getGaz()));
        return ligneCommande;
    }
}
