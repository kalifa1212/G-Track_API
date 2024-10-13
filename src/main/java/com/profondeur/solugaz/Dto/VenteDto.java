package com.profondeur.solugaz.Dto;

import com.profondeur.solugaz.Model.*;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;
import java.util.stream.Collectors;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class VenteDto {
    private Integer id;
    private Date dateVendu;
    private int quantite;
    private BigDecimal prix;
    private BigDecimal prixUnitaire;
    private LocalisationDto localisation;
    private GazDto gaz;
    private DistributeurDto distributeur;

    public static VenteDto fromEntity(Vente vente) {
        if(vente==null) {
            return null;
        }
        return VenteDto.builder()
                .id(vente.getId())
                .dateVendu(vente.getDateVendu())
                .quantite(vente.getQuantite())
                .prix(vente.getPrix())
                .prixUnitaire(vente.getPrixUnitaire())
                .localisation(LocalisationDto.fromEntity(vente.getLocalisation()))
                .gaz(GazDto.fromEntity(vente.getGaz()))
                .distributeur(DistributeurDto.fromEntity(vente.getDistributeur()))
                .build();
    }
    public static Vente toEntity(VenteDto venteDto) {
        if(venteDto==null) {
            return null;
        }
        //retourneMosque(venteDto.getFollowedMosques());
        Vente vente = new Vente();
        vente.setId(venteDto.getId());
        vente.setDateVendu(venteDto.getDateVendu());
        vente.setQuantite(venteDto.getQuantite());
        vente.setPrix(venteDto.getPrix());
        vente.setPrixUnitaire(venteDto.getPrixUnitaire());
        vente.setGaz(GazDto.toEntity(venteDto.getGaz()));
        vente.setDistributeur(DistributeurDto.toEntity(venteDto.getDistributeur()));
        vente.setLocalisation(LocalisationDto.toEntity(venteDto.getLocalisation()));
        return vente;
    }
}
