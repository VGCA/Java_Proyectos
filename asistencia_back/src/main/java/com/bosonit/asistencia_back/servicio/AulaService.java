package com.bosonit.asistencia_back.servicio;

import java.util.Optional;

import com.bosonit.asistencia_back.modelo.Asistencia;
import com.bosonit.asistencia_back.modelo.Aula;
import com.bosonit.asistencia_back.modelo.Estudiante;

public interface AulaService {

    Optional<Aula> buscarAulaPorId(Long id);
    Optional<Asistencia> buscarAsistenciaPorCodigo(String codigo);
    Optional<Estudiante> buscarEstudiantePorCodigo(String codigo);
    void registrarAsistencia(Asistencia asistencia);
    void actualizarAsistencia(Asistencia asistencia);

}
