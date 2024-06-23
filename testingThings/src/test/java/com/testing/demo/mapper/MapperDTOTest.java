package com.testing.demo.mapper;

import com.testing.demo.dto.PersonaDTO;
import com.testing.demo.model.Persona;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(MockitoExtension.class)
class MapperDTOTest {

    private Persona persona;
    private PersonaDTO personaDTO;
    private MapperDTO mapperDTO;

    @BeforeEach
    void setUp() {
        persona = new Persona();
        personaDTO = new PersonaDTO();
        mapperDTO = new MapperDTO();

        persona.setEdad(12);
        persona.setNombre("firstNombre");
        persona.setId(1);
        persona.setPuesto("Informático");

        personaDTO.setPuesto("Informático");
        personaDTO.setNombre("firstNombre");
    }

    @Test
    void changePersonaToDto() {
        PersonaDTO personaDTO1 =
                mapperDTO.changePersonaToDto(persona);
        assertEquals(personaDTO,personaDTO1);
    }
}