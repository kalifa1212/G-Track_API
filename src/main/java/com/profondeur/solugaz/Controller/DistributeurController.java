package com.profondeur.solugaz.Controller;

import com.profondeur.solugaz.Controller.Api.DistributeurApi;
import com.profondeur.solugaz.Dto.DistributeurDto;
import com.profondeur.solugaz.Model.Dataset;
import com.profondeur.solugaz.Repository.DistributeurRepository;
import com.profondeur.solugaz.Services.DatasetService;
import com.profondeur.solugaz.Services.DistributeurService;
import com.profondeur.solugaz.Services.WekaService;
import lombok.extern.slf4j.Slf4j;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.Point;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;


@RestController
public class DistributeurController implements DistributeurApi {
    private DistributeurService distributeurService;
    private DistributeurRepository distributeurRepository;
    private DatasetService datasetService;
    private WekaService wekaService;

    @Autowired
    public DistributeurController(
            DistributeurService distributeurService,
            DistributeurRepository distributeurRepository,
            DatasetService datasetService,
            WekaService wekaService
    ) {
        this.distributeurService = distributeurService;
        this.distributeurRepository=distributeurRepository;
        this.datasetService=datasetService;
        this.wekaService=wekaService;
    }
    @Override
    public ResponseEntity<DistributeurDto> save(DistributeurDto dto) {
        return ResponseEntity.ok(distributeurService.save(dto));
    }

    @Override
    public DistributeurDto findById(Integer id) {
        return distributeurService.findById(id);
    }

    @Override
    public List<Dataset> DataGeneration() throws Exception {
        wekaService.loadFile();
        return null;
       // return datasetService.saveHistoryAndDataGeneration();
    }

    @Override
    public Page<DistributeurDto> findByNom(String nom, String sortColumn, int page, int taille, String sortDirection) {
        Pageable paging = PageRequest.of(page, taille, Sort.by(sortColumn).ascending());

        return distributeurService.findByNom(paging,nom);
    }

    @Override
    public List<DistributeurDto> testGeo( double longitude, double latitude) {

        return distributeurRepository.findNearest(longitude,latitude).stream().map(DistributeurDto::fromEntity).collect(Collectors.toList());
    }

    @Override
    public List<DistributeurDto> testGeoSecondMethod(int distance, double longitude, double latitude) {
        GeometryFactory geometryFactory = new GeometryFactory();
        Point point = geometryFactory.createPoint(new Coordinate(longitude, latitude));
        return distributeurRepository.findNearestCities(point,20).stream().map(DistributeurDto::fromEntity).collect(Collectors.toList());
    }

    @Override
    public Page<DistributeurDto> findByVille(String ville, String sortColumn, int page, int taille, String sortDirection) {
        Pageable paging = PageRequest.of(page, taille, Sort.by(sortColumn).ascending());

        return distributeurService.findByville(paging,ville);
    }


    @Override
    public Page<DistributeurDto> findAll(String sortColumn, int page, int taille, String sortDirection) {
        Pageable paging = PageRequest.of(page, taille, Sort.by(sortColumn).ascending());
        return distributeurService.findAll(paging);
    }

    @Override
    public void delete(Integer id) {
        distributeurService.delete(id);
    }
}
