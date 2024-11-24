package com.profondeur.solugaz.Dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.profondeur.solugaz.Model.*;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.Point;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Data @Builder
@AllArgsConstructor @NoArgsConstructor
public class DistributeurDto {

    private Integer id;
    private String nom;
    private String description;
    private double longitude;
    private double latitude;
    private double altitude;
    private LocalisationDto localisation;
    //private Set<GazDto> gaz;
    private Set<StockDto> stock= new HashSet<>();
    //private StockDto stock;
    @JsonIgnore
    private Point location;


    public static DistributeurDto fromEntity(Distributeur distributeur) {
         final GeometryFactory geometryFactory = new GeometryFactory();
        if(distributeur==null) {
            return null;
        }
        if (distributeur.getStock()==null){
            distributeur.setStock(new HashSet<>());
        }
        Point point = geometryFactory.createPoint(new Coordinate(distributeur.getLongitude(), distributeur.getLatitude()));
        return DistributeurDto.builder()
                .id(distributeur.getId())
                .nom(distributeur.getNom())
                .description(distributeur.getDescription())
                .longitude(distributeur.getLongitude())
                .altitude(distributeur.getAltitude())
                .localisation(LocalisationDto.fromEntity(distributeur.getLocalisation()))
                .stock(distributeur.getStock().stream().map(StockDto::fromEntity).collect(Collectors.toSet()))
                .latitude(distributeur.getLatitude())
                .location(point)
                .build();
    }
    public static Distributeur toEntity(DistributeurDto distributeurDto) {
        final GeometryFactory geometryFactory = new GeometryFactory();
        if(distributeurDto==null) {
            return null;
        }
        if (distributeurDto.getStock()==null){
            distributeurDto.setStock(new HashSet<>());
        }
        Point point = geometryFactory.createPoint(new Coordinate(distributeurDto.getLongitude(), distributeurDto.getLatitude()));
        Distributeur distributeur = new Distributeur();
        distributeur.setId(distributeurDto.getId());
        distributeur.setNom(distributeurDto.getNom());
        distributeur.setAltitude(distributeurDto.getAltitude());
        distributeur.setLatitude(distributeurDto.getLatitude());
        distributeur.setLongitude(distributeurDto.getLongitude());
        distributeur.setLocation(point);
        distributeur.setDescription(distributeurDto.getDescription());
        distributeur.setStock(distributeurDto.getStock().stream().map(StockDto::toEntity).collect(Collectors.toSet()));
        distributeur.setLocalisation(LocalisationDto.toEntity(distributeurDto.getLocalisation()));
        return distributeur;
    }
}
