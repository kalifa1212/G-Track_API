package com.profondeur.solugaz.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Table(name = "gaz")
@Entity
@EqualsAndHashCode(callSuper=true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Gaz extends AbstractEntity{
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private String fabricant;
    private TypeGaz type;
    @ManyToOne
    private Distributeur distributeur;
}
