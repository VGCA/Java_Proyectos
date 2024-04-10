package com.bosonit.Kafka.servicio;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class Consumer {

    @KafkaListener(topics="mytopic",groupId = "mygroup")
    public void receptor(String message){
        System.out.println("Mensaje recibido "+message);
    }
}
