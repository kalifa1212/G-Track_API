package com.profondeur.solugaz.Controller;

import com.profondeur.solugaz.Controller.Api.UtilisateurApi;
import com.profondeur.solugaz.Dto.UtilisateurDto;
import com.profondeur.solugaz.Dto.auth.AuthenticationRequest;
import com.profondeur.solugaz.Dto.auth.AuthenticationResponse;
import com.profondeur.solugaz.Exceptions.InvalidEntityException;
import com.profondeur.solugaz.Model.Utilisateur;
import com.profondeur.solugaz.Repository.UtilisateurRepository;
import com.profondeur.solugaz.Services.UtilisateurService;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@CrossOrigin
@RestController
@OpenAPIDefinition(info = @Info(title = "SoluGaz API", version = "1.0"))
@Slf4j
public class UtilisateurController implements UtilisateurApi {

    private UtilisateurRepository utilisateurRepository;
    private UtilisateurService utilisateurService;

    @Autowired
    public UtilisateurController(
            UtilisateurRepository utilisateurRepository,
            UtilisateurService utilisateurService
    ) {
        this.utilisateurRepository = utilisateurRepository;
        this.utilisateurService=utilisateurService;
    }


    public ResponseEntity<AuthenticationResponse> authentification(@RequestBody AuthenticationRequest authenticationRequest) {

        return ResponseEntity.ok(AuthenticationResponse.builder()
                .accessToken("")
                .email("email")
                .build());
    }

    @Override
    public ResponseEntity<UtilisateurDto> save(UtilisateurDto dto, Boolean update) {
        // TODO Enregistrement d'un utilisateur ou mis a jour utilisateur

        return ResponseEntity.ok(utilisateurService.save(dto,update));
    }

    @Override
    public UtilisateurDto findById(Integer id) {
        // TODO recherche utilisateur par id
        return utilisateurService.findById(id);
    }

    @Override
    public UtilisateurDto findByEmail(String email, HttpServletRequest request) {
        // TODO Recherche  Utilisateur by email

        Optional<Utilisateur> util = utilisateurRepository.findUtilisateurByEmail(email);
        if(util.isEmpty()){
            throw new InvalidEntityException("L'email n'existe pas");
        }
        Utilisateur utilisateur=util.get();
        UtilisateurDto dto = UtilisateurDto.fromEntity(utilisateur);
        return dto;
    }

    @Override
    public Boolean GranteCompteRole(String email, String role) {
        Boolean result=utilisateurService.GranteRole(email,role);
        return result;
    }


    @Override
    public Page<UtilisateurDto> findAll(String sortColumn, int page, int taille, String sortDirection) {
        Pageable paging = PageRequest.of(page, taille, Sort.by(sortColumn).ascending());
        // TODO afficher tout les utilisateurs
        return utilisateurService.findAll(paging);
    }

    @Override
    public void delete(Integer id) {
        // TODO Suppprimer utilisateur

        utilisateurService.delete(id);
    }
}