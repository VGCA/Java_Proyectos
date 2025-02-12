package com.pruebatecnica.Prueba.tecnica.Senior.kafka;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import com.pruebatecnica.Prueba.tecnica.Senior.entity.HotelSearch;

@Service
public class KafkaProducerService {

    private static final String TOPIC = "hotel_availability_searches";
    private final KafkaTemplate<String, HotelSearch> kafkaTemplate;

    public KafkaProducerService(KafkaTemplate<String, HotelSearch> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendMessage(HotelSearch search) {
        kafkaTemplate.send(TOPIC, search);
    }
}
