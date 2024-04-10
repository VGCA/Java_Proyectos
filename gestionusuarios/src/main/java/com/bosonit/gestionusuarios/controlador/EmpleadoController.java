package com.bosonit.gestionusuarios.controlador;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bosonit.gestionusuarios.excepciones.RecursoNoEncontrado;
import com.bosonit.gestionusuarios.modelo.Empleado;
import com.bosonit.gestionusuarios.repo.EmpleadoRepo;

@RestController
@RequestMapping("/api/")
@CrossOrigin(origins = "http://localhost:4200/")
public class EmpleadoController {

    @Autowired
    private EmpleadoRepo empleadoRepo;

    @GetMapping("/empleados")
    public List<Empleado> listarEmpleados() {
        return empleadoRepo.findAll();
    }

    @PostMapping("/empleados")
    public Empleado guardarEmpleado(@RequestBody Empleado empleado) {
        return empleadoRepo.save(empleado);
    }

    @GetMapping("/empleados/{id}")
    public ResponseEntity<Empleado> obtenerEmpleado(@PathVariable int id) {
        Empleado empleado = empleadoRepo.findById(id)
                .orElseThrow(() -> new RecursoNoEncontrado("No existe el empleado deseado"));
        return ResponseEntity.ok(empleado);
    }

    @PutMapping("/empleados")
    public ResponseEntity<Empleado> actualizarEmpleado(@PathVariable int id, @RequestBody Empleado detallesEmpleado) {
        Empleado empleado = empleadoRepo.findById(id)
                .orElseThrow(() -> new RecursoNoEncontrado("No existe el empleado deseado"));

        empleado.setNombre(detallesEmpleado.getNombre());
        empleado.setApellido(detallesEmpleado.getApellido());
        empleado.setEmail(detallesEmpleado.getEmail());

        Empleado empleadoUpdate = empleadoRepo.save(empleado);

        return ResponseEntity.ok(empleadoUpdate);
    }

    @DeleteMapping("/empleados/{id}")
    public ResponseEntity<Map<String, Boolean>> eliminarEmpleado(@PathVariable int id) {
        Empleado empleado = empleadoRepo.findById(id)
                .orElseThrow(() -> new RecursoNoEncontrado("No existe el empleado deseado"));

        empleadoRepo.delete(empleado);
        Map<String, Boolean> respuesta = new HashMap<>();
        respuesta.put("eliminar", Boolean.TRUE);
        return ResponseEntity.ok(respuesta);
    }

}
