package com.profondeur.solugaz.Services;

import com.profondeur.solugaz.Dto.UtilisateurDto;
import com.profondeur.solugaz.Model.Utilisateur;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface    UtilisateurService {

    UtilisateurDto save(UtilisateurDto  dto,boolean update);
    UtilisateurDto findById(Integer id);
    UtilisateurDto findByEmail(String email);
    Optional<Utilisateur> UniqueMail(String email);
    Page<UtilisateurDto> findAll(Pageable page);
    Boolean GranteRole(String Email,String role);
    void delete(Integer id);
}
