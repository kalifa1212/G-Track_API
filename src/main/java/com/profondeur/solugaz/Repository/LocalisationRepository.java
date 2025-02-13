package com.profondeur.solugaz.Repository;

import com.profondeur.solugaz.Model.Localisation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LocalisationRepository extends JpaRepository<Localisation,Integer> {
	
	Page<Localisation> findLocalisationByVille(String ville, Pageable page);
	//List<Localisation> findLocalisationByQuartier(String quartier);
	Page<Localisation> findLocalisationByPaysLike(String pays, Pageable page);
	Page<Localisation> findLocalisationByVilleLike(String str,Pageable page);
}
