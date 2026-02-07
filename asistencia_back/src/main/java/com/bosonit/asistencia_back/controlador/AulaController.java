package com.bosonit.asistencia_back.controlador;

import com.bosonit.asistencia_back.modelo.Estudiante;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.bosonit.asistencia_back.modelo.Asistencia;
import com.bosonit.asistencia_back.modelo.Aula;
import com.bosonit.asistencia_back.servicio.AulaService;

import java.util.Optional;

@CrossOrigin
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/aulas")
public class AulaController {

    private final AulaService aulaService;

    @GetMapping("/buscar-aula/{id}")
    public ResponseEntity<Aula> buscarPorId(@PathVariable Long id) {
        return aulaService.buscarAulaPorId(id)
                .map(aula -> new ResponseEntity<>(aula, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/registrar-asistencia")
    public ResponseEntity<Object> registrarAsistencia(@RequestBody Asistencia asistencia) {
        String codigo = asistencia.getEstudiante().getCodigo();

        Optional<Estudiante> estudianteOpt = aulaService.buscarEstudiantePorCodigo(codigo);
        if (estudianteOpt.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No se encontró estudiante con código: " + codigo);
        }

        if (aulaService.buscarAsistenciaPorCodigo(codigo).isPresent()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("No se puede registrar 2 veces la asistencia.");
        }

        asistencia.setEstudiante(estudianteOpt.get());
        aulaService.registrarAsistencia(asistencia);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/registrar-salida")
    public ResponseEntity<Object> actualizarAsistencia(@RequestBody Asistencia asistencia) {
        String codigo = asistencia.getEstudiante().getCodigo();

        return aulaService.buscarEstudiantePorCodigo(codigo)
                .map(estudiante -> aulaService.buscarAsistenciaPorCodigo(codigo)
                        .map(asistenciaExistente -> {

                            aulaService.actualizarAsistencia(asistenciaExistente);
                            return ResponseEntity.ok().build();
                        })
                        .orElseGet(() -> ResponseEntity.status(HttpStatus.BAD_REQUEST)
                                .body("No hay asistencia registrada")))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("No se encontró estudiante con código: " + codigo));
    }

    @GetMapping("/buscar-asistencia/{codigoEstudiante}")
    public ResponseEntity<Object> buscarAsistenciaPorCodigo(@PathVariable String codigoEstudiante) {
        return aulaService.buscarAsistenciaPorCodigo(codigoEstudiante)
                .<ResponseEntity<Object>>map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("No se encontró asistencia con ese código: " + codigoEstudiante));
    }

}
