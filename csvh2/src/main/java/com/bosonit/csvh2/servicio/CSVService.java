package com.bosonit.csvh2.servicio;

import java.io.IOException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.bosonit.csvh2.ayuda.CSVHelper;
import com.bosonit.csvh2.modelo.Tutorial;
import com.bosonit.csvh2.repositorio.TutorialRepository;

@Service
public class CSVService {

    private final TutorialRepository repository;

    public CSVService(TutorialRepository repository) {
        this.repository = repository;
    }

    public void save(MultipartFile file) throws IOException {
        try {
            List<Tutorial> tutorials = CSVHelper.csvToTutorials(file.getInputStream());
            repository.saveAll(tutorials);
        } catch (IOException e) {
            throw new IOException("fail to store csv data: " + e.getMessage());
        }
    }

    public List<Tutorial> getAllTutorials() {
        return repository.findAll();
    }
}