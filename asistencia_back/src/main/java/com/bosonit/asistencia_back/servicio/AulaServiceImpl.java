package com.bosonit.asistencia_back.servicio;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bosonit.asistencia_back.modelo.Asistencia;
import com.bosonit.asistencia_back.modelo.Aula;
import com.bosonit.asistencia_back.modelo.Estudiante;
import com.bosonit.asistencia_back.repositorio.AsistenciaRepo;
import com.bosonit.asistencia_back.repositorio.AulaRepo;
import com.bosonit.asistencia_back.repositorio.EstudianteRepo;

import java.time.LocalDate;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class AulaServiceImpl implements AulaService {

    /**
     * Inyección de dependencias por el constructor
     * gracias a Lombok en vez de usar AutoWired
     */
    private final AsistenciaRepo asistenciaRepo;
    private final EstudianteRepo estudianteRepo;
    private final AulaRepo aulaRepo;

    @Override
    public Optional<Aula> buscarAulaPorId(Long id) {
        return aulaRepo.findById(id);
    }

    @Override
    public Optional<Asistencia> buscarAsistenciaPorCodigo(String codigo) {
        LocalDate fechaActual = LocalDate.now();
        return asistenciaRepo.findByEstudiante_CodigoAndFechaIngreso(codigo, fechaActual);
    }

    @Override
    public Optional<Estudiante> buscarEstudiantePorCodigo(String codigo) {
        return estudianteRepo.findByCodigo(codigo);
    }

    @Override
    public void registrarAsistencia(Asistencia asistencia) {
        LocalDate fechaActual = LocalDate.now();
        asistencia.setFechaIngreso(fechaActual);
        asistencia.setIngresoConfirmado(true);
        asistencia.setSalidaConfirmado(false);
        asistenciaRepo.save(asistencia);
    }

    @Override
    public void actualizarAsistencia(Asistencia asistencia) {
        asistencia.setSalidaConfirmado(true);
        asistenciaRepo.save(asistencia);
    }
}
