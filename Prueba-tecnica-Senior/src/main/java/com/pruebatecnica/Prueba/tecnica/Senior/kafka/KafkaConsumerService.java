package com.pruebatecnica.Prueba.tecnica.Senior.kafka;

import com.pruebatecnica.Prueba.tecnica.Senior.entity.HotelSearch;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import com.pruebatecnica.Prueba.tecnica.Senior.repository.HotelSearchRepository;

@Service
public class KafkaConsumerService {

    private final HotelSearchRepository repository;

    public KafkaConsumerService(HotelSearchRepository repository) {
        this.repository = repository;
    }

    @KafkaListener(topics = "hotel_availability_searches", groupId = "hotel-group")
    public void consume(HotelSearch search) {
        repository.save(search);
    }
}
