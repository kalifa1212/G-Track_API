package com.profondeur.solugaz.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Date;

@Table(name = "stock")
@Entity
@EqualsAndHashCode(callSuper=true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Stock extends AbstractEntity{
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private long quantite;
    private Date date;
    private String motif;

    @ManyToOne
    private Gaz gaz;

}
