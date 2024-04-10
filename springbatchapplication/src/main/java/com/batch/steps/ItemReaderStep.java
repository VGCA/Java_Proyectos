package com.batch.steps;

import java.io.FileReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.lang.Nullable;

import com.batch.entity.Person;
import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ItemReaderStep implements Tasklet {

    @Autowired
    private ResourceLoader resourceLoader;

    @Override
    @Nullable
    public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception {
        log.info("Inicio del paso de lectura");
        Reader reader = new FileReader(resourceLoader.getResource("classpath:files/destination/persons.csv")
                .getFile());
        CSVParser parser = new CSVParserBuilder()
                .withSeparator(',').build();
        CSVReader csvReader = new CSVReaderBuilder(reader)
                .withCSVParser(parser)
                .withSkipLines(1)
                .build();

        List<Person> personList = new ArrayList<>();
        String[] linea;
        while ((linea = csvReader.readNext()) != null) {
            Person person = new Person();
            person.setName(linea[0]);
            person.setLastname(linea[1]);
            person.setAge(Integer.parseInt(linea[2]));
            personList.add(person);
        }

        csvReader.close();
        reader.close();
        log.info("Fin del paso de lectura");
        chunkContext.getStepContext()
        .getStepExecution()
        .getJobExecution()
        .getExecutionContext()
        .put("personList", personList);
        return RepeatStatus.FINISHED;
    }

}
