package com.profondeur.solugaz.Model;

import com.profondeur.solugaz.Model.Enum.CommandeStatus;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Table(name = "commande") @Entity
@EqualsAndHashCode(callSuper=true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Commande extends AbstractEntity{
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private LocalDateTime dateCommande;
    private String reference;
    private double montant;
    private CommandeStatus status;
    @ManyToOne
    private Utilisateur utilisateur;
    @OneToMany(mappedBy = "commande")
    private List<LigneCommande> ligneCommande;

}
