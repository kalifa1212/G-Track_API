package com.profondeur.solugaz.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;

@Table(name = "dataset") @Entity
@Data
@NoArgsConstructor @AllArgsConstructor
public class Dataset {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private LocalDate date;
    private Integer quantiteVendu;
    private TypeGaz typeGaz;
    private Integer tailleDuFoyer;
    private Fabricant fabricant;
    private String saison;
    private String evenementLocaux;
    private String region;

}
