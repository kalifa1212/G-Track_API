package com.profondeur.solugaz.Controller.Api;

import com.profondeur.solugaz.Dto.DistributeurDto;
import com.profondeur.solugaz.Dto.UtilisateurDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.profondeur.solugaz.Constant.Constants.DISTRIBUTEUR_ENDPOINT;
import static com.profondeur.solugaz.Constant.Constants.UTILISATEUR_ENDPOINT;

@CrossOrigin(origins = "*")
public interface DistributeurApi {

    @SecurityRequirement(name = "Bearer Authentication")
    @Operation(summary = "Enregistrer un distributeur ",description = "permet d'enregistrer un distributeur")
    @ApiResponses(value={
            @ApiResponse(responseCode = "200",description = "Enregistrer",content = {
                    @Content(mediaType ="application/json",schema = @Schema(implementation = DistributeurDto.class))
            }),
            @ApiResponse(responseCode = "401",description = " non Autoriser",content = @Content),
            @ApiResponse(responseCode = "400",description = " Invalide",content = @Content)
    })
    @PostMapping(value = DISTRIBUTEUR_ENDPOINT+"nouveau")
    ResponseEntity<DistributeurDto> save(@RequestBody DistributeurDto dto);

    @Operation(summary = "Recherche ",description = "Recherche par ID")
    @SecurityRequirement(name = "Bearer Authentication")
    @GetMapping(value=DISTRIBUTEUR_ENDPOINT+"findBy/id/{idutilisateur}")
    DistributeurDto findById(@PathVariable("idutilisateur") Integer id);
    @Operation(summary = "Recherche ",description = "Recherche par Nom")
    @SecurityRequirement(name = "Bearer Authentication")
    @GetMapping(value=DISTRIBUTEUR_ENDPOINT+"findBy/nom/{nom}")
    DistributeurDto findByNom(@PathVariable("nom") String nom);

    @Operation(summary = "Recherche ",description = "afficher")
    @SecurityRequirement(name = "Bearer Authentication")
    @GetMapping(value=DISTRIBUTEUR_ENDPOINT+"find/all",produces= MediaType.APPLICATION_JSON_VALUE)
    Page<DistributeurDto> findAll(@RequestParam(required = false,defaultValue = "nom") String sortColumn,
                                 @RequestParam(defaultValue = "0") int page,
                                 @RequestParam(defaultValue = "2") int taille,
                                 @RequestParam(defaultValue = "ascending") String sortDirection);


    // TODO delete
    @SecurityRequirement(name = "Bearer Authentication")
    @DeleteMapping(value=DISTRIBUTEUR_ENDPOINT+"supprimer/{id}")
    void delete(@PathVariable("id")Integer id);
}
