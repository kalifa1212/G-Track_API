package com.profondeur.solugaz.Dto;

import com.profondeur.solugaz.Model.Distributeur;
import com.profondeur.solugaz.Model.TypeGaz;
import com.profondeur.solugaz.Model.Gaz;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.stream.Collectors;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GazDto {
    private Integer id;
    private String fabricant;
    private TypeGaz type;
    //private DistributeurDto distributeur;

    public static GazDto fromEntity(Gaz gaz) {
        if(gaz==null) {
            return null;
        }
        return GazDto.builder()
                .id(gaz.getId())
                .fabricant(gaz.getFabricant())
                .type(gaz.getType())
                //.distributeur(DistributeurDto.fromEntity(gaz.getDistributeur()))
                .build();
    }
    public static Gaz toEntity(GazDto gazDto) {
        if(gazDto==null) {
            return null;
        }
        //retourneMosque(gazDto.getFollowedMosques());
        Gaz gaz = new Gaz();
        gaz.setId(gazDto.getId());
        gaz.setType(gazDto.getType());
        gaz.setFabricant(gazDto.getFabricant());
        //gaz.setDistributeur(DistributeurDto.toEntity(gazDto.getDistributeur()));
        return gaz;
    }
}
