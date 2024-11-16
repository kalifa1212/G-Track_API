package com.profondeur.solugaz.Model;

import com.profondeur.solugaz.Model.Enum.Fabricant;
import com.profondeur.solugaz.Model.Enum.TypeGaz;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Table(name = "ligne_commande") @Entity
@EqualsAndHashCode(callSuper=true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LigneCommande extends AbstractEntity{
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    @ManyToOne
    private Commande commande;
    private Integer quantite;
    private double prix_unitaire;
    @ManyToOne
    private Gaz gaz;
    @ManyToOne
    private Distributeur distributeur;
}
