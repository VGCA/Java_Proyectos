package com.bosonit.Kafka.servicio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class Producer {
    public static final String mensaje = "mytopic";

    @Autowired
    private KafkaTemplate<String,String> kafkaTemp;

    public void publicitar(String message){
        System.out.println("Publicando el mensaje "+mensaje);
        this.kafkaTemp.send(mensaje,message);
    }
}
