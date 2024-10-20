package com.profondeur.solugaz.Controller.Api;

import com.profondeur.solugaz.Dto.DistributeurDto;
import com.profondeur.solugaz.Dto.VenteDto;
import com.profondeur.solugaz.Model.TypeGaz;
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

import java.util.List;

import static com.profondeur.solugaz.Constant.Constants.*;

@CrossOrigin(origins = "*")
public interface VenteApi {

    @SecurityRequirement(name = "Bearer Authentication")
    @Operation(summary = "Enregistrer une Vente ",description = "permet d'enregistrer une vente")
    @ApiResponses(value={
            @ApiResponse(responseCode = "200",description = "Enregistrer",content = {
                    @Content(mediaType ="application/json",schema = @Schema(implementation = VenteDto.class))
            }),
            @ApiResponse(responseCode = "401",description = " non Autoriser",content = @Content),
            @ApiResponse(responseCode = "400",description = " Invalide",content = @Content)
    })
    @PostMapping(value = VENTE_ENDPOINT+"nouveau")
    ResponseEntity<VenteDto> save(@RequestBody VenteDto dto);

    @Operation(summary = "Recherche ",description = "Recherche par ID")
    @SecurityRequirement(name = "Bearer Authentication")
    @GetMapping(value=VENTE_ENDPOINT+"findBy/id/{idutilisateur}")
    VenteDto findById(@PathVariable("idutilisateur") Integer id);
    @Operation(summary = "Recherche ",description = "Recherche par ID")
    @SecurityRequirement(name = "Bearer Authentication")
    @GetMapping(value=VENTE_ENDPOINT+"findBy/gaz/{id}")
    List<VenteDto> findVenteByGaz(@PathVariable("id") Integer id);
    @Operation(summary = "Recherche ",description = "Recherche par ID")
    @SecurityRequirement(name = "Bearer Authentication")
    @GetMapping(value=VENTE_ENDPOINT+"findBy/distributeur/{id}")
    List<VenteDto> findVenteByDistributeur(@PathVariable("id") Integer id);
    @Operation(summary = "Recherche ",description = "Recherche par ID")
    @SecurityRequirement(name = "Bearer Authentication")
    @GetMapping(value=VENTE_ENDPOINT+"findBy/localisation/{id}")
    List<VenteDto> findVenteByLocalisationId(@PathVariable("id") Integer id);
    @Operation(summary = "Recherche ",description = "Recherche par ID")
    @SecurityRequirement(name = "Bearer Authentication")
    @GetMapping(value=VENTE_ENDPOINT+"findBy/localisationDetails/")
    List<VenteDto> findVentesByLocalisationDetail(@RequestParam() Integer idLocalisation,
                                                  @RequestParam() TypeGaz typeGaz,
                                                  @RequestParam() String fabricant);
    @Operation(summary = "Recherche ",description = "Recherche par ID")
    @SecurityRequirement(name = "Bearer Authentication")
    @GetMapping(value=VENTE_ENDPOINT+"findBy/distributeurDetails/")
    List<VenteDto> findVentesBydistributeurDetails(@RequestParam() Integer idDistributeur,
                                                   @RequestParam() TypeGaz typeGaz,
                                                   @RequestParam() String fabricant);

    @Operation(summary = "Recherche ",description = "afficher")
    @SecurityRequirement(name = "Bearer Authentication")
    @GetMapping(value=VENTE_ENDPOINT+"find/all",produces= MediaType.APPLICATION_JSON_VALUE)
    Page<VenteDto> findAll(@RequestParam(required = false,defaultValue = "dateVendu") String sortColumn,
                                  @RequestParam(defaultValue = "0") int page,
                                  @RequestParam(defaultValue = "2") int taille,
                                  @RequestParam(defaultValue = "ascending") String sortDirection);


    // TODO delete
    @SecurityRequirement(name = "Bearer Authentication")
    @DeleteMapping(value=VENTE_ENDPOINT+"supprimer/{id}")
    void delete(@PathVariable("id")Integer id);
}
