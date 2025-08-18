package com.bosonit.kafka.servicio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.logging.Logger;

@Service
public class Producer {

    private Logger logger = Logger.getLogger(Producer.class.getName());

    private String messageTopic = "mytopic";

    private final KafkaTemplate<String,String> kafkaTemp;

    @Autowired
    public Producer(KafkaTemplate<String, String> kafkaTemp) {
        this.kafkaTemp = kafkaTemp;
    }

    public void publicitar(String message){
        logger.info(messageTopic);
        this.kafkaTemp.send(mensaje,message);
    }
}
