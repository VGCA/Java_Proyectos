package com.testing.demo.controller;

import com.testing.demo.dto.PersonaDTO;
import com.testing.demo.model.Persona;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;

import org.mockito.junit.jupiter.MockitoExtension;
import static org.assertj.core.api.Assertions.assertThat;


@ExtendWith(MockitoExtension.class)
public class ControllerTest {
    @InjectMocks
    private ObjetoController controller;
    private Persona persona;

    @BeforeEach
    public void init(){
        persona=new Persona();
        persona.setPuesto("newPuesto");
        persona.setEdad(10);
        persona.setId(1);
        persona.setNombre("newNombre");
    }

    public void testingMapperDto(){
        PersonaDTO personaToDTO = controller.changePersonaToDto(persona);
    }
}
