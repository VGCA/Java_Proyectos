package com.bosonit.futbol_back_eureka.servicio;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bosonit.futbol_back_eureka.modelo.Jugador;
import com.bosonit.futbol_back_eureka.repositorio.JugadoresRepo;


@Service
public class JugadoresServicio {

    @Autowired
    public JugadoresRepo jugadoresRepo;

    /**
     * VER TODOS LOS JUGADORES
     * 
     * @return
     */
    public List<Jugador> listaJugadores() {
        return jugadoresRepo.findAll();
    }

    /**
     * AGREGAR UN NUEVO JUGADOR
     * 
     * @param jugador
     * @return
     */
    public Jugador agregarJugador(Jugador jugador) {
        return jugadoresRepo.save(jugador);
    }

    /**
     * BORRAR JUGADOR POR ID
     * 
     * @param id
     */
    public void borrarJugador(int id) {
        Jugador encontrado = this.buscarJugadorId(id);
        jugadoresRepo.delete(encontrado);
    }

    /**
     * ENCUENTRA UN JUGADOR POR SU ID
     * 
     * @param id
     * @return
     */
    public Jugador buscarJugadorId(int id) {
        return jugadoresRepo.findById(id).orElse(null);
    }

    /**
     * ACTUALIZA UN JUGADOR DISPONIBLE
     * 
     * @param id
     * @param jugador
     * @return
     */
    public Jugador actualizarJugador(int id, Jugador jugador) {
        Jugador encontrado = this.buscarJugadorId(id);
        encontrado.setNombre(jugador.getNombre());
        encontrado.setEquipo(jugador.getEquipo());
        return encontrado;
    }
}
