package com.pruebatecnica.prueba.tecnica.senior.adapter.out.kafka;

import com.pruebatecnica.prueba.tecnica.senior.adapter.messaging.SearchMessage;
import com.pruebatecnica.prueba.tecnica.senior.application.port.out.SearchEventPublisherPort;
import com.pruebatecnica.prueba.tecnica.senior.domain.model.HotelSearch;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class KafkaSearchPublisher implements SearchEventPublisherPort {

    private static final String TOPIC = "hotel_availability_searches";
    private final KafkaTemplate<String, SearchMessage> kafkaTemplate;

    public KafkaSearchPublisher(KafkaTemplate<String, SearchMessage> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @Override
    public void publish(HotelSearch search) {
        kafkaTemplate.send(TOPIC, SearchMessage.from(search));
    }
}
