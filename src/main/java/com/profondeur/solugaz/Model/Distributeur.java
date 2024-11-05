package com.profondeur.solugaz.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.locationtech.jts.geom.Point;

import java.util.Set;

@NamedEntityGraph(name = "distributeur.stock", attributeNodes = {
        @NamedAttributeNode("stock")
})
@Table(name = "distributeur") @Entity
@EqualsAndHashCode(callSuper=true)
@Data @NoArgsConstructor @AllArgsConstructor
public class Distributeur extends AbstractEntity{

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private String nom;
    private String description;
    private double longitude;
    private double latitude;
    private double altitude;

    @Column(columnDefinition = "geography(Point, 4326)")
    private Point location;

    @ManyToOne
    private Localisation localisation;
    @OneToMany(mappedBy = "distributeur",fetch = FetchType.EAGER)
    private Set<Stock> stock;
}
