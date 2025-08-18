package com.bosonit.kafka.controlador;

import com.bosonit.kafka.servicio.Producer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/kafkaapp")
public class KafkaController {

    private final Producer producer;

    @Autowired
    public KafkaController(Producer producer) {
        this.producer = producer;
    }

    @PostMapping(value="/post")
    public void enviarMensaje(@RequestParam("msg") String msg){
        this.producer.publicitar(msg);
    }
}
