package com.profondeur.solugaz.Controller;

import com.profondeur.solugaz.Controller.Api.VenteApi;
import com.profondeur.solugaz.Dto.VenteDto;
import com.profondeur.solugaz.Model.Enum.TypeGaz;
import com.profondeur.solugaz.Services.VenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class VenteController implements VenteApi {

    private VenteService venteService;


    @Autowired
    public VenteController(
            VenteService venteService
    ) {
        this.venteService = venteService;
    }
    @Override
    public ResponseEntity<VenteDto> save(VenteDto dto) {
        return ResponseEntity.ok(venteService.save(dto));
    }

    @Override
    public ResponseEntity<String> commandePayment(Integer idCommande) {
        venteService.commandePayment(idCommande);
        return  ResponseEntity.ok("Payement par commande validé");
    }

    @Override
    public VenteDto findById(Integer id) {
        return venteService.findById(id);
    }

    @Override
    public List<VenteDto> findVenteByGaz(Integer id) {
        return venteService.findVenteByGaz(id);
    }

    @Override
    public List<VenteDto> findVenteByDistributeur(Integer id) {
        return venteService.findVenteByDistributeur(id);
    }

    @Override
    public List<VenteDto> findVenteByLocalisationId(Integer id) {
        return venteService.findVenteByLocalisationId(id);
    }

    @Override
    public List<VenteDto> findVentesByLocalisationDetail(Integer idLocalisation, TypeGaz typeGaz, String fabricant) {
        return venteService.findVentesByLocalisationDetail(idLocalisation,typeGaz,fabricant);
    }

    @Override
    public List<VenteDto> findVentesBydistributeurDetails(Integer idDistributeur, TypeGaz typeGaz, String fabricant) {
        return venteService.findVentesBydistributeurDetails(idDistributeur, typeGaz, fabricant);
    }

    @Override
    public Page<VenteDto> findAll(String sortColumn, int page, int taille, String sortDirection) {
        Pageable paging = PageRequest.of(page, taille, Sort.by(sortColumn).ascending());

        return venteService.findAll(paging);
    }

    @Override
    public void delete(Integer id) {
        venteService.delete(id);
    }
}
