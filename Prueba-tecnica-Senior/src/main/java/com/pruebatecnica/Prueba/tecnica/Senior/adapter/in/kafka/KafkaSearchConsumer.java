package com.pruebatecnica.prueba.tecnica.senior.adapter.in.kafka;

import com.pruebatecnica.prueba.tecnica.senior.adapter.messaging.SearchMessage;
import com.pruebatecnica.prueba.tecnica.senior.application.port.in.SaveSearchUseCase;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class KafkaSearchConsumer {

    private final SaveSearchUseCase saveSearchUseCase;

    public KafkaSearchConsumer(SaveSearchUseCase saveSearchUseCase) {
        this.saveSearchUseCase = saveSearchUseCase;
    }

    @KafkaListener(topics = "hotel_availability_searches", groupId = "hotel-group")
    public void consume(SearchMessage message) {
        saveSearchUseCase.saveSearch(message.toDomain());
    }
}
