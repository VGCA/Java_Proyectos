package com.pruebatecnica.Prueba_Tecnica_TecData.config;

import com.pruebatecnica.Prueba_Tecnica_TecData.model.Price;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.pruebatecnica.Prueba_Tecnica_TecData.repository.PriceRepository;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Configuration
public class DataLoader {

    @Bean
    CommandLineRunner initDatabase(PriceRepository repository) {
        return args -> {
            repository.save(new Price( 1, LocalDateTime.parse("2020-06-14T00:00:00"),
                    LocalDateTime.parse("2020-12-31T23:59:59"), 1, 35455, 0, new BigDecimal("35.50"), "EUR"));

            repository.save(new Price( 1, LocalDateTime.parse("2020-06-14T15:00:00"),
                    LocalDateTime.parse("2020-06-14T18:30:00"), 2, 35455, 1, new BigDecimal("25.45"), "EUR"));

            repository.save(new Price( 1, LocalDateTime.parse("2020-06-15T00:00:00"),
                    LocalDateTime.parse("2020-06-15T11:00:00"), 3, 35455, 1, new BigDecimal("30.50"), "EUR"));

            repository.save(new Price( 1, LocalDateTime.parse("2020-06-15T16:00:00"),
                    LocalDateTime.parse("2020-12-31T23:59:59"), 4, 35455, 1, new BigDecimal("38.95"), "EUR"));
        };
    }
}

