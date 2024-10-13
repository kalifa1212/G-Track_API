package com.profondeur.solugaz.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Set;

@Table(name = "distributeur")
@Entity
@EqualsAndHashCode(callSuper=true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Distributeur extends AbstractEntity{

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private String nom;
    private String description;
    private double longitude;
    private double latitude;
    private double altitude;

    @ManyToOne
    private Localisation localisation;
    @OneToMany(mappedBy = "distributeur")
    private Set<Gaz> pointsDeVente;
    @ManyToOne
    private Stock stock;
}
