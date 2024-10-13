package com.profondeur.solugaz.Dto;

import com.profondeur.solugaz.Model.Gaz;
import com.profondeur.solugaz.Model.Stock;
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
public class StockDto {
    private Integer id;
    private long quantite;
    private Date date;
    private String motif;
    private GazDto gaz;

    public static StockDto fromEntity(Stock stock) {
        if(stock==null) {
            return null;
        }
        return StockDto.builder()
                .id(stock.getId())
                .quantite(stock.getQuantite())
                .date(stock.getDate())
                .motif(stock.getMotif())
                .gaz(GazDto.fromEntity(stock.getGaz()))
                .build();
    }
    public static Stock toEntity(StockDto stockDto) {
        if(stockDto==null) {
            return null;
        }
        //retourneMosque(stockDto.getFollowedMosques());
        Stock stock = new Stock();
        stock.setId(stockDto.getId());
        stock.setDate(stockDto.getDate());
        stock.setQuantite(stockDto.getQuantite());
        stock.setMotif(stockDto.getMotif());
        stock.setGaz(GazDto.toEntity(stockDto.getGaz()));
        return stock;
    }
}
