package com.bosonit.springmongo.controlador;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bosonit.springmongo.modelo.Reserva;
import com.bosonit.springmongo.repositorio.ReservaRepo;

@RestController
@RequestMapping("/reserva")
public class ReservaController {

    private final ReservaRepo reservaRepo;

    public ReservaController(ReservaRepo reservaRepo) {
        this.reservaRepo = reservaRepo;
    }

    @PostMapping("/reservar")
    public Reserva hacerReserva(@RequestBody Reserva reserva) {
        return reservaRepo.hacerReserva(reserva);
    }

    @DeleteMapping("/eliminar")
    public void eliminarReserva(@RequestBody Reserva reserva) {
        reservaRepo.eliminarReserva(reserva);
    }

    @PutMapping("/update")
    public Reserva updateReserva(@RequestBody Reserva reserva) {
        return reservaRepo.updateReserva(reserva);
    }

    @GetMapping("/listar")
    public List<Reserva> getAllReservas() {
        return reservaRepo.getAllReservas();
    }

    @DeleteMapping("/borrartodo")
    public void eliminarTodo() {
        reservaRepo.borrarTodo();
    }
}
