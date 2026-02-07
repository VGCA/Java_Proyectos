package com.example.producingwebservice;

import static org.junit.jupiter.api.Assertions.*;

import io.spring.guides.gs_producing_web_service.President;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class PresidentRepositoryTest {

    private PresidentRepository repository;

    @BeforeEach
    void setUp() {
        repository = new PresidentRepository();
    }

    @Test
    void shouldReturnPresidentWhenNameExists() {
        String name = "Jaime";
        President result = repository.findPresident(name);

        assertNotNull(result);
        assertEquals(name, result.getName());
    }

    @Test
    void shouldReturnNullWhenNameDoesNotExist() {
        assertNull(repository.findPresident("NonExistent"));
    }

    @Test
    void shouldThrowExceptionWhenNameIsNull() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            repository.findPresident(null);
        });

        assertEquals("The president's name must not be null", exception.getMessage());
    }
}
