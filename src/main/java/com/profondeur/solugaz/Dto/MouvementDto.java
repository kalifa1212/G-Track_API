package com.profondeur.solugaz.Dto;

import com.profondeur.solugaz.Model.Stock;
import com.profondeur.solugaz.Model.TypeMouvement;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MouvementDto {

    private TypeMouvement typeMouvement;
    private long quantite;
    private Date date;
    private String motif;
    private Stock stock;
}
