package com.kafka.consumer.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.KafkaListener;

@Configuration
public class KafkaConsumerListener {

    private Logger logger = LoggerFactory.getLogger(KafkaConsumerListener.class);

    /**
     * Method receiving message from the provider
     * @param message from the provider
     */
    @KafkaListener(topics = {"unProgramadorNace-topic"}, groupId = "my-groupd-id")
    public void listener(String message){
        logger.info("Mensaje recibido: "+message);
    }
}
