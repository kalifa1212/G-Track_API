package com.profondeur.solugaz.Dto;

import com.profondeur.solugaz.Model.Distributeur;
import com.profondeur.solugaz.Model.Gaz;
import com.profondeur.solugaz.Model.Localisation;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class VenteDto {
    private Date dateVendu;
    private int quantite;
    private BigDecimal prix;
    private BigDecimal prixUnitaire;
    private Localisation localisation;
    private Gaz gaz;
    private Distributeur distributeur;
}
