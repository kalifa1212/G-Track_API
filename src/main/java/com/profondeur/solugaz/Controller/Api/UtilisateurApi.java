package com.profondeur.solugaz.Controller.Api;

import com.profondeur.solugaz.Dto.UtilisateurDto;
import com.profondeur.solugaz.Dto.auth.AuthenticationRequest;
import com.profondeur.solugaz.Dto.auth.AuthenticationResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.profondeur.solugaz.Constant.Constants.AUTHENTICATION_ENDPOINT;
import static com.profondeur.solugaz.Constant.Constants.UTILISATEUR_ENDPOINT;

@CrossOrigin(origins = "*")
public interface UtilisateurApi {

    // TODO Non fontionnel
    @Operation(summary = "Authentication ",description = "Connexion")
    @PostMapping(value = AUTHENTICATION_ENDPOINT+"authenticate")
    ResponseEntity<AuthenticationResponse> authentification(@RequestBody AuthenticationRequest authenticationRequest);

    @SecurityRequirement(name = "Bearer Authentication")
    @Operation(summary = "Enregistrer un utilisateur ",description = "permet d'enregistrer un utilisateur")
    @ApiResponses(value={
            @ApiResponse(responseCode = "200",description = "Enregistrer",content = {
                    @Content(mediaType ="application/json",schema = @Schema(implementation = UtilisateurDto.class))
            }),
            @ApiResponse(responseCode = "401",description = "Utilisateur non Autoriser",content = @Content),
            @ApiResponse(responseCode = "400",description = "Utilisateur Invalide",content = @Content)
    })
    @PostMapping(value = UTILISATEUR_ENDPOINT+"nouveau/{update}")
    ResponseEntity<UtilisateurDto> save(@RequestBody UtilisateurDto  dto, @PathVariable("update") Boolean update);

    @Operation(summary = "Recherche ",description = "Recherche par ID")
    @SecurityRequirement(name = "Bearer Authentication")
    @GetMapping(value=UTILISATEUR_ENDPOINT+"find/id/{idutilisateur}")
    UtilisateurDto findById(@PathVariable("idutilisateur") Integer id);

    @Operation(summary = "Recherche ",description = "Recherche par Email")
    @SecurityRequirement(name = "Bearer Authentication")
    @GetMapping(value=UTILISATEUR_ENDPOINT+"find/email/{email}")
    UtilisateurDto findByEmail(@PathVariable("email") String email, HttpServletRequest request);
    //  HttpServletRequest request to implement ip addresse of user

    @Operation(summary = "Grant ",description = "Grante role {root,admin,staff,user }")
    @SecurityRequirement(name = "Bearer Authentication")
    @GetMapping(value=UTILISATEUR_ENDPOINT+"grantrole/{email}/{role}")
    Boolean GranteCompteRole(@PathVariable("email") String email,@PathVariable("role") String role);

    @Operation(summary = "Recherche ",description = "afficher tout les utilisateur")
    @SecurityRequirement(name = "Bearer Authentication")
    @GetMapping(value=UTILISATEUR_ENDPOINT+"find/all",produces=MediaType.APPLICATION_JSON_VALUE)
    Page<UtilisateurDto> findAll(@RequestParam(required = false,defaultValue = "nom") String sortColumn,
                                 @RequestParam(defaultValue = "0") int page,
                                 @RequestParam(defaultValue = "2") int taille,
                                 @RequestParam(defaultValue = "ascending") String sortDirection);


    // TODO delete
    @SecurityRequirement(name = "Bearer Authentication")
    @DeleteMapping(value=UTILISATEUR_ENDPOINT+"supprimer/{id}")
    void delete(@PathVariable("id")Integer id);
}
