package com.profondeur.solugaz.Dto;

import com.profondeur.solugaz.Model.*;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;
import java.util.stream.Collectors;

@Data @Builder
@AllArgsConstructor @NoArgsConstructor
public class DistributeurDto {

    private Integer id;
    private String nom;
    private String description;
    private double longitude;
    private double latitude;
    private double altitude;
    private LocalisationDto localisation;
    private Set<GazDto> gaz;
    private StockDto stock;

    public static DistributeurDto fromEntity(Distributeur distributeur) {
        if(distributeur==null) {
            return null;
        }
        return DistributeurDto.builder()
                .id(distributeur.getId())
                .nom(distributeur.getNom())
                .description(distributeur.getDescription())
                .longitude(distributeur.getLongitude())
                .altitude(distributeur.getAltitude())
                .localisation(LocalisationDto.fromEntity(distributeur.getLocalisation()))
                .stock(StockDto.fromEntity(distributeur.getStock()))
                .latitude(distributeur.getLatitude())
                .build();
    }
    public static Distributeur toEntity(DistributeurDto distributeurDto) {
        if(distributeurDto==null) {
            return null;
        }
        Distributeur distributeur = new Distributeur();
        distributeur.setId(distributeurDto.getId());
        distributeur.setNom(distributeurDto.getNom());
        distributeur.setAltitude(distributeurDto.getAltitude());
        distributeur.setLatitude(distributeurDto.getLatitude());
        distributeur.setLongitude(distributeurDto.getLongitude());
        distributeur.setDescription(distributeurDto.getDescription());
        distributeur.setStock(StockDto.toEntity(distributeurDto.getStock()));
        distributeur.setLocalisation(LocalisationDto.toEntity(distributeurDto.getLocalisation()));
        return distributeur;
    }
}
