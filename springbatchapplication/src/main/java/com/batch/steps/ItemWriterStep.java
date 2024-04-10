package com.batch.steps;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;

import com.batch.entity.Person;
import com.batch.service.PersonService;

import java.util.List;

@Slf4j
public class ItemWriterStep implements Tasklet{

    @Autowired
    private PersonService personService;

    @Override
    @Nullable
    public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception {

        log.info("-------------------> Inicio del paso de escritura de los datos <-------------------");

        List<Person> personList = (List<Person>) chunkContext.getStepContext().getStepExecution().getJobExecution().getExecutionContext().get("personList");

        personList.forEach( person -> {
            if(person != null){
                log.info(person.toString());
            }
        });

        personService.saveAll(personList);

        log.info("-------------------> Fin del paso de escritura de los datos <-------------------");

        return RepeatStatus.FINISHED;
    }
    
}
