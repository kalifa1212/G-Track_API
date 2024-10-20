package com.profondeur.solugaz.Repository;

import com.profondeur.solugaz.Model.Distributeur;
import com.profondeur.solugaz.Model.TypeGaz;
import com.profondeur.solugaz.Model.Vente;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface VenteRepository extends JpaRepository<Vente, Integer> {

    Page<Vente> findAll(Pageable pageable);
    List<Vente> findVenteByGazId(Integer pageable);
    List<Vente> findVenteByDistributeurId(Integer pageable);
    List<Vente> findVenteByLocalisationId(Integer pageable);

    @Query("SELECT v FROM Vente v INNER JOIN v.gaz g INNER JOIN v.localisation l WHERE l.id = :idLocalisation AND g.type = :typeGaz AND g.fabricant = :fabricant")
    List<Vente> findVentesByLocalisation(@Param("idLocalisation") Integer idLocalisation,
                                                                  @Param("typeGaz") TypeGaz typeGaz,
                                                                  @Param("fabricant") String fabricant);
    @Query("SELECT v FROM Vente v INNER JOIN v.gaz g INNER JOIN v.distributeur d WHERE d.id = :idDistributeur AND g.type = :typeGaz AND g.fabricant = :fabricant")
    List<Vente> findVentesBydistributeur(@Param("idDistributeur") Integer idDistributeur,
                                                                  @Param("typeGaz") TypeGaz typeGaz,
                                                                  @Param("fabricant") String fabricant);

}
