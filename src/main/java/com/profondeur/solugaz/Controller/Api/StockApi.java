package com.profondeur.solugaz.Controller.Api;

import com.profondeur.solugaz.Dto.DistributeurDto;
import com.profondeur.solugaz.Dto.StockDto;
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
public interface StockApi {

    @SecurityRequirement(name = "Bearer Authentication")
    @Operation(summary = "Enregistrer un stock ",description = "permet d'enregistrer un stock")
    @ApiResponses(value={
            @ApiResponse(responseCode = "200",description = "Enregistrer",content = {
                    @Content(mediaType ="application/json",schema = @Schema(implementation = StockDto.class))
            }),
            @ApiResponse(responseCode = "401",description = " non Autoriser",content = @Content),
            @ApiResponse(responseCode = "400",description = " Invalide",content = @Content)
    })
    @PostMapping(value = STOCK_ENDPOINT+"nouveau")
    ResponseEntity<StockDto> save(@RequestBody StockDto dto);

    @Operation(summary = "Recherche ",description = "Recherche par ID")
    @SecurityRequirement(name = "Bearer Authentication")
    @GetMapping(value=STOCK_ENDPOINT+"findBy/id/{idutilisateur}")
    StockDto findById(@PathVariable("idutilisateur") Integer id);

    @Operation(summary = "Recherche ",description = "afficher")
    @SecurityRequirement(name = "Bearer Authentication")
    @GetMapping(value=STOCK_ENDPOINT+"find/all",produces= MediaType.APPLICATION_JSON_VALUE)
    Page<StockDto> findAll(@RequestParam(required = false,defaultValue = "date") String sortColumn,
                                  @RequestParam(defaultValue = "0") int page,
                                  @RequestParam(defaultValue = "2") int taille,
                                  @RequestParam(defaultValue = "ascending") String sortDirection);

    @Operation(summary = "Recherche ",description = "afficher")
    @SecurityRequirement(name = "Bearer Authentication")
    @GetMapping(value=STOCK_ENDPOINT+"findBy/fabricant",produces= MediaType.APPLICATION_JSON_VALUE)
    Page<StockDto> findByGazFabricant(@RequestParam(required = false,defaultValue = "date") String sortColumn,
                           @RequestParam(defaultValue = "0") int page,
                           @RequestParam(defaultValue = "2") int taille,
                           @RequestParam(defaultValue = "ascending") String sortDirection,
                           @RequestParam(defaultValue = "total") String fabricant
                                      );
    @Operation(summary = "Recherche ",description = "afficher ECO_GAZ,GAZ_12KG, GAZ_AUTRE")
    @SecurityRequirement(name = "Bearer Authentication")
    @GetMapping(value=STOCK_ENDPOINT+"findBy/type",produces= MediaType.APPLICATION_JSON_VALUE)
    List<StockDto> findStock(@RequestParam(required = false,defaultValue = "5") int quantite,
                            @RequestParam(defaultValue = "GAZ_12KG") TypeGaz typeGaz,
                            @RequestParam(defaultValue = "total") String fabricant);


    // TODO delete
    @SecurityRequirement(name = "Bearer Authentication")
    @DeleteMapping(value=STOCK_ENDPOINT+"supprimer/{id}")
    void delete(@PathVariable("id")Integer id);
}
