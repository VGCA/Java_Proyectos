package com.bosonit.crudrelaciones.infrastructure.config;

import com.bosonit.crudrelaciones.application.service.ProfesorService;
import com.bosonit.crudrelaciones.application.service.StudentService;
import com.bosonit.crudrelaciones.domain.port.in.ProfesorUseCase;
import com.bosonit.crudrelaciones.domain.port.in.StudentUseCase;
import com.bosonit.crudrelaciones.domain.port.out.ProfesorRepositoryPort;
import com.bosonit.crudrelaciones.domain.port.out.StudentRepositoryPort;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfiguration {

    @Bean
    public StudentUseCase studentUseCase(StudentRepositoryPort repositorio) {
        return new StudentService(repositorio);
    }

    @Bean
    public ProfesorUseCase profesorUseCase(ProfesorRepositoryPort repositorio) {
        return new ProfesorService(repositorio);
    }
}
