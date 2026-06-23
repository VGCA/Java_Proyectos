package com.springbatch.listener;

import org.springframework.batch.core.SkipListener;
import org.springframework.batch.item.file.FlatFileParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CustomSkipListener implements SkipListener<Object, Object> {

    private static final Logger logger = LoggerFactory.getLogger(CustomSkipListener.class);

    @Override
    public void onSkipInRead(Throwable t) {
        if (t instanceof FlatFileParseException) {
            FlatFileParseException parseException = (FlatFileParseException) t;
            logger.info("ERROR en LECTURA: Linea {}: Error de formato para la siguiente entrada: {}",
                    parseException.getLineNumber(), parseException.getInput());
        } else {
            logger.info("ERROR en LECTURA: {}", t.getMessage());
        }
    }

    @Override
    public void onSkipInWrite(Object item, Throwable t) {
        logger.info("ERROR en ESCRITURA: {}", t.getMessage());
    }

    @Override
    public void onSkipInProcess(Object item, Throwable t) {
        logger.info("ERROR en PROCESADO: {}", t.getMessage());
    }
}