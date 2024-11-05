package com.profondeur.solugaz.Repository;

import com.profondeur.solugaz.Model.Distributeur;
import org.locationtech.jts.geom.Point;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DistributeurRepository extends JpaRepository<Distributeur, Integer> {
    //TODO GEOLOCALISATION
    @Query("SELECT d FROM Distributeur d WHERE function('ST_DWithin', d.location, :point, :distance) = true")
    List<Distributeur> findNearestCities(Point point, double distance);
    @Query(value = "SELECT * FROM Distributeur ORDER BY ST_Distance(location, ST_SetSRID(ST_MakePoint(:longitude, :latitude), 4326)) LIMIT 3", nativeQuery = true)
    List<Distributeur> findNearest(double longitude, double latitude);
    @EntityGraph(value = "distributeur.stock") // TODO a verifier l'efficacité
    Page<Distributeur> findAll(Pageable pageable);
    Page<Distributeur> findAllByLocalisationVille(Pageable pageable,String ville);
    Page<Distributeur> findAllByNomContaining(Pageable pageable,String nom);
}
