package com.profondeur.solugaz.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Date;

@Table(name = "mouvement")
@Entity
@EqualsAndHashCode(callSuper=true)
@Data @NoArgsConstructor @AllArgsConstructor
public class Mouvement extends AbstractEntity{
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private TypeMouvement typeMouvement;
    private long quantite;
    private Date date;
    private String motif;
    @ManyToOne
    private Stock stock;

}
