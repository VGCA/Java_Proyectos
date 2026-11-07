package com.bosonit.crudrelaciones.infrastructure.adapter.out.persistence.mapper;

import com.bosonit.crudrelaciones.domain.model.Profesor;
import com.bosonit.crudrelaciones.infrastructure.adapter.out.persistence.entity.ProfesorEntity;

public final class ProfesorMapper {

    private ProfesorMapper() {
    }

    public static Profesor toDomain(ProfesorEntity e) {
        return new Profesor(e.getId(), e.getIdProfesor(), e.getIdPersona(), e.getComents(), e.getBranch());
    }

    public static ProfesorEntity toEntity(Profesor p) {
        return new ProfesorEntity(p.id(), p.id_profesor(), p.id_persona(), p.coments(), p.branch());
    }
}
