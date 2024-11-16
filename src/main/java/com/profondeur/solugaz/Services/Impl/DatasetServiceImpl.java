package com.profondeur.solugaz.Services.Impl;

import com.profondeur.solugaz.Model.Dataset;
import com.profondeur.solugaz.Model.Enum.Fabricant;
import com.profondeur.solugaz.Model.Enum.TypeGaz;
import com.profondeur.solugaz.Repository.DatasetRepository;
import com.profondeur.solugaz.Services.DatasetService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Random;

@Slf4j
@Service
public class DatasetServiceImpl implements DatasetService {

    @Autowired
    private DatasetRepository datasetRepository;

    @Autowired
    public DatasetServiceImpl(
            DatasetRepository datasetRepository
    ) {
        this.datasetRepository=datasetRepository;
    }

    @Override
    public List<Dataset> saveHistoryAndDataGeneration() {
        for(int i=0; i<50;i++){
            Dataset dataset =GenerationOfData();
            datasetRepository.save(dataset);
        }
        return datasetRepository.findAll();
    }
    public Dataset GenerationOfData(){
        String[] region = {"centre", "adamaoua", "extreme-nord", "nord", "sud", "sud-ouest", "nord-ouest", "ouest","litorale","est"};
        String[] saison = {"seche", "pluvieuse", "transition"};
        String[] evenement = {"fete tabaski", "decembre", "pacque"};
        Dataset dataset=new Dataset();
        TypeGaz[] typeGazs = TypeGaz.values();
        Fabricant[] fabricants = Fabricant.values();
        Random random = new Random();
        LocalDate startDate = LocalDate.of(2010, 1, 1);
        LocalDate endDate = LocalDate.of(2024, 12, 31);
        dataset.setDate(generateRandomDate(startDate,endDate));
        dataset.setRegion(selectionnerAleatoire(region));
        dataset.setSaison(selectionnerAleatoire(saison));
        dataset.setEvenementLocaux(selectionnerAleatoire(evenement));
        dataset.setFabricant(fabricants[random.nextInt(fabricants.length)]);
        dataset.setQuantiteVendu(genererNombreAleatoire(1,1000));
        dataset.setTypeGaz(typeGazs[random.nextInt(typeGazs.length)]);
        dataset.setTailleDuFoyer(genererNombreAleatoire(1,12));
        return dataset;
    }
    //TODO generate random number
    public static Integer genererNombreAleatoire(int min, int max) {
        if (max <= min) {
            throw new IllegalArgumentException("max doit être supérieur à min");
        }

        Random random = new Random();
        return random.nextInt(max - min + 1) + min;
    }

    //TODO Date Generator
    public static LocalDate generateRandomDate(LocalDate startDate, LocalDate endDate) {
        long daysBetween = ChronoUnit.DAYS.between(startDate, endDate);
        int randomDays = new Random().nextInt((int) daysBetween);
        return startDate.plusDays(randomDays);
    }
    //TODO string random of table
    public static String selectionnerAleatoire(String[] tableau) {
        if (tableau == null || tableau.length == 0) {
            return null; // Ou lever une exception
        }

        Random random = new Random();
        int indexAleatoire = random.nextInt(tableau.length);
        return tableau[indexAleatoire];
    }
    //TODO usage
//    String[] fruits = {"pomme", "banane", "orange", "cerise"};
//    String fruitAleatoire = selectionnerAleatoire(fruits);


    //TODO date random usage
//    LocalDate startDate = LocalDate.of(2023, 1, 1);
//    LocalDate endDate = LocalDate.of(2023, 12, 31);
//
//    LocalDate randomDate = generateRandomDate(startDate, endDate);
    //TODO Date format definition
//    String pattern = "yyyy-MM-dd";
//    SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
}
