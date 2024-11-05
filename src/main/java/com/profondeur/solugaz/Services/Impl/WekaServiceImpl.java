package com.profondeur.solugaz.Services.Impl;

import com.profondeur.solugaz.Services.WekaService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.File;

@Slf4j
@Service
public class WekaServiceImpl implements WekaService {
    @Override
    public void loadFile() throws Exception {
        File csvFile= new File("D:\\HAMIDOU\\Projets\\Projet_Personnel\\Api\\Solugaz\\dataset.csv");
        log.info("file loade success {}",csvFile.getName());

//        CSVLoader loader = new CSVLoader();
//        loader.setSource(csvFile);
//        Instances data = loader.getDataSet();
//        log.info("transform to data instances");
// Encodage one-hot des variables nominales
//        NominalToBinary filter = new NominalToBinary();
//        filter.setInputFormat(data);
//        Instances filteredData = Filter.useFilter(data, filter);
//        log.info("encodage ok");

    }
}
