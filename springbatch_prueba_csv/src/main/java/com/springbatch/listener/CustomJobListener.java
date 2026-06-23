package com.springbatch.listener;

import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CustomJobListener implements JobExecutionListener{

    private static final Logger logger = Logger.getLogger(CustomJobListener.class.getName());

    @Override
    public void beforeJob(JobExecution jobExecution) {
        logger.log(Level.INFO, "Se va a ejecutar el Job con ID: ");
    }

    @Override
    public void afterJob(JobExecution jobExecution) {
        logger.log(Level.INFO,"Se ha terminado de ejecutar el Job con ID: ");
    }

}
