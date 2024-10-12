package com.profondeur.solugaz.Dto;

import com.profondeur.solugaz.Model.Distributeur;
import com.profondeur.solugaz.Model.type;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GazDto {
    private String fabricant;
    private com.profondeur.solugaz.Model.type type;
    private Distributeur distributeur;
}
