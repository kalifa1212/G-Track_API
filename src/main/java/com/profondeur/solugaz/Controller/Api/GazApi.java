package com.profondeur.solugaz.Controller.Api;

import com.profondeur.solugaz.Dto.DistributeurDto;
import com.profondeur.solugaz.Dto.GazDto;
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
public interface GazApi {

    @SecurityRequirement(name = "Bearer Authentication")
    @Operation(summary = "Enregistrer un Gaz ",description = "permet d'enregistrer un Gaz")
    @ApiResponses(value={
            @ApiResponse(responseCode = "200",description = "Enregistrer",content = {
                    @Content(mediaType ="application/json",schema = @Schema(implementation = DistributeurDto.class))
            }),
            @ApiResponse(responseCode = "401",description = " non Autoriser",content = @Content),
            @ApiResponse(responseCode = "400",description = " Invalide",content = @Content)
    })
    @PostMapping(value = GAZ_ENDPOINT+"nouveau")
    ResponseEntity<GazDto> save(@RequestBody GazDto dto);

    @Operation(summary = "Recherche ",description = "Recherche par ID")
    @SecurityRequirement(name = "Bearer Authentication")
    @GetMapping(value=GAZ_ENDPOINT+"findBy/id/{idutilisateur}")
    GazDto findById(@PathVariable("idutilisateur") Integer id);
    @Operation(summary = "Recherche ",description = "Recherche par type ECO_GAZ,GAZ_12KG,  GAZ_AUTRE")
    @SecurityRequirement(name = "Bearer Authentication")
    @GetMapping(value=GAZ_ENDPOINT+"findBy/type/{type}")
    List<GazDto> findByType(@PathVariable("type") TypeGaz type);
    @Operation(summary = "Recherche ",description = "Recherche par ID")
    @SecurityRequirement(name = "Bearer Authentication")
    @GetMapping(value=GAZ_ENDPOINT+"findBy/fabricant/{fabricant}")
    List<GazDto> findByFabricant(@PathVariable("fabricant") String fabricant);

    @Operation(summary = "Recherche ",description = "afficher")
    @SecurityRequirement(name = "Bearer Authentication")
    @GetMapping(value=GAZ_ENDPOINT+"find/all",produces= MediaType.APPLICATION_JSON_VALUE)
    Page<GazDto> findAll(@RequestParam(required = false,defaultValue = "fabricant") String sortColumn,
                                  @RequestParam(defaultValue = "0") int page,
                                  @RequestParam(defaultValue = "2") int taille,
                                  @RequestParam(defaultValue = "ascending") String sortDirection);


    // TODO delete
    @SecurityRequirement(name = "Bearer Authentication")
    @DeleteMapping(value=GAZ_ENDPOINT+"supprimer/{id}")
    void delete(@PathVariable("id")Integer id);
}
