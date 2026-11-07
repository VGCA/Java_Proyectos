package com.bosonit.crudrelaciones.infrastructure.adapter.out.persistence.mapper;

import com.bosonit.crudrelaciones.domain.model.Student;
import com.bosonit.crudrelaciones.infrastructure.adapter.out.persistence.entity.StudentEntity;

public final class StudentMapper {

    private StudentMapper() {
    }

    public static Student toDomain(StudentEntity e) {
        return new Student(e.getId(), e.getNameStudent(), e.getIdPersona(), e.getComents(), e.getIdProfesor());
    }

    public static StudentEntity toEntity(Student s) {
        return new StudentEntity(s.id(), s.name_student(), s.id_persona(), s.coments(), s.id_profesor());
    }
}
