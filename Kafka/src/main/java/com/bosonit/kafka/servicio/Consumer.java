package com.bosonit.kafka.servicio;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.logging.Logger;

@Service
public class Consumer {

    private Logger logger = Logger.getLogger(Consumer.class.getName());

    @KafkaListener(topics="mytopic",groupId = "mygroup")
    public void receptor(String message){
        logger.info(message);
    }
}
