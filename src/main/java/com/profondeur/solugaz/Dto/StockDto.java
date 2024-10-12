package com.profondeur.solugaz.Dto;

import com.profondeur.solugaz.Model.Gaz;
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
public class StockDto {
    private long quantite;
    private Date date;
    private String motif;
    private Gaz gaz;
}
