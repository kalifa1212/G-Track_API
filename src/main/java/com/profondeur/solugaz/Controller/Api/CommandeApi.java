package com.profondeur.solugaz.Controller.Api;

import com.profondeur.solugaz.Dto.CommandeDto;
import com.profondeur.solugaz.Dto.LigneCommandeDto;
import com.profondeur.solugaz.Dto.VenteDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import static com.profondeur.solugaz.Constant.Constants.*;

@CrossOrigin(origins = "*")
public interface CommandeApi {

    @SecurityRequirement(name = "Bearer Authentication")
    @Operation(summary = " commande Et ligne de commande",description = "permet d'enregistrer une commande")
    @ApiResponses(value={
            @ApiResponse(responseCode = "200",description = "Enregistrer",content = {
                    @Content(mediaType ="application/json",schema = @Schema(implementation = CommandeDto.class))
            }),
            @ApiResponse(responseCode = "401",description = " non Autoriser",content = @Content),
            @ApiResponse(responseCode = "400",description = " Invalide",content = @Content)
    })
    @PostMapping(value = COMMANDE_ENDPOINT+"nouveau")
    ResponseEntity<CommandeDto> saveCommande(@RequestBody CommandeDto dto);

    @SecurityRequirement(name = "Bearer Authentication")
    @Operation(summary = " commande Et ligne de commande",description = "permet d'enregistrer une commande")
    @ApiResponses(value={
            @ApiResponse(responseCode = "200",description = "Enregistrer",content = {
                    @Content(mediaType ="application/json",schema = @Schema(implementation = LigneCommandeDto.class))
            }),
            @ApiResponse(responseCode = "401",description = " non Autoriser",content = @Content),
            @ApiResponse(responseCode = "400",description = " Invalide",content = @Content)
    })
    @PostMapping(value = LIGNE_COMMANDE_ENDPOINT+"nouveau")
    ResponseEntity<LigneCommandeDto> saveLigneCommande(@RequestBody LigneCommandeDto dto);
}
