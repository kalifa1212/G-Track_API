package com.profondeur.solugaz.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

@Table(name = "vente")
@Entity
@EqualsAndHashCode(callSuper=true)
@Data @NoArgsConstructor @AllArgsConstructor
public class Vente extends AbstractEntity{
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private Date dateVendu;
    private int quantite;
    private int  prix;
    private int prixUnitaire;

    @ManyToOne
    private Localisation localisation;
    @ManyToOne
    private Gaz gaz;
    @ManyToOne
    private Distributeur distributeur;

}
