package com.profondeur.solugaz.Dto;

import com.profondeur.solugaz.Model.Stock;
import com.profondeur.solugaz.Model.TypeMouvement;
import com.profondeur.solugaz.Model.Mouvement;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.stream.Collectors;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MouvementDto {

    private Integer id;
    private TypeMouvement typeMouvement;
    private long quantite;
    private Date date;
    private String motif;
    private StockDto stock;

    public static MouvementDto fromEntity(Mouvement mouvement) {
        if(mouvement==null) {
            return null;
        }
        return MouvementDto.builder()
                .id(mouvement.getId())
                .typeMouvement(mouvement.getTypeMouvement())
                .quantite(mouvement.getQuantite())
                .date(mouvement.getDate())
                .motif(mouvement.getMotif())
                .stock(StockDto.fromEntity(mouvement.getStock()))
                .build();
    }
    public static Mouvement toEntity(MouvementDto mouvementDto) {
        if(mouvementDto==null) {
            return null;
        }
        //retourneMosque(mouvementDto.getFollowedMosques());
        Mouvement mouvement = new Mouvement();
        mouvement.setId(mouvementDto.getId());
        mouvement.setDate(mouvementDto.getDate());
        mouvement.setTypeMouvement(mouvementDto.getTypeMouvement());
        mouvement.setQuantite(mouvementDto.getQuantite());
        mouvement.setStock(StockDto.toEntity(mouvementDto.getStock()));
        return mouvement;
    }
}
