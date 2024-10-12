package com.profondeur.solugaz.Dto;

import com.profondeur.solugaz.Model.Gaz;
import com.profondeur.solugaz.Model.Localisation;
import com.profondeur.solugaz.Model.Stock;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DistributeurDto {
    private String nom;
    private String Description;
    private double longitude;
    private double latitude;
    private double altitude;
    private Localisation localisation;
    private Set<Gaz> pointsDeVente;
    private Stock stock;
}
