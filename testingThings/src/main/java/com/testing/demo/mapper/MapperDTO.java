package com.testing.demo.mapper;

import com.testing.demo.dto.PersonaDTO;
import com.testing.demo.model.Persona;

public class MapperDTO {

    /**
     * Function to return dto from object
     * @param persona object to transform
     * @return dto from object
     */
    public PersonaDTO changePersonaToDto(Persona persona){
        PersonaDTO personaDTO = new PersonaDTO();
        personaDTO.setNombre(persona.getNombre());
        personaDTO.setPuesto(persona.getPuesto());
        return personaDTO;
    }

}
