package com.kafka.provider;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.core.KafkaTemplate;

@SpringBootApplication
public class ProviderApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProviderApplication.class, args);
    }

    /**
     * Method to send a message to the consumer
     * @param kafkaTemplate autowired parameter thaks to bean flag
     * @return message sent
     */
    @Bean
    CommandLineRunner init(KafkaTemplate<String, String> kafkaTemplate) {
        return args -> {
            kafkaTemplate.send("unProgramadorNace-topic", "Prueba final con Kafka");
        };
    }
}
