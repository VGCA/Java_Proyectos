package com.bosonit.futbol_back_eureka.controlador;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.bosonit.futbol_back_eureka.modelo.Equipo;
import com.bosonit.futbol_back_eureka.modelo.Jugador;
import com.bosonit.futbol_back_eureka.servicio.EquipoServicio;
import com.bosonit.futbol_back_eureka.servicio.JugadoresServicio;


@CrossOrigin // Para implementarle luego Angular
@RestController
public class JugadoresController {

    @Autowired
    public JugadoresServicio jugadoresServicio;

    @Autowired
    public EquipoServicio equipoServicio;

    /**
     * FUNCIÓN PARA VER SI TODO FUNCIONA
     * 
     * @return
     */
    @GetMapping("/saludo")
    public String saludo() {
        return "Todo ok";
    }

    /* Jugadores controlador */

    @GetMapping("/lista_jugadores")
    public List<Jugador> verJugadores() {
        return jugadoresServicio.listaJugadores();
    }

    @PostMapping("/agregar_jugador")
    public Jugador agregarJugador(@RequestBody Jugador jugador) {
        return jugadoresServicio.agregarJugador(jugador);
    }

    @DeleteMapping("/borrar_jugador/{id}")
    public void borrarJugador(@PathVariable int id) {
        jugadoresServicio.borrarJugador(id);
    }

    @PutMapping("/actualizar_jugador/{id}")
    public Jugador actualizarJugador(@PathVariable int id, @RequestBody Jugador jugador) {
        return jugadoresServicio.actualizarJugador(id, jugador);
    }

    /* Equipo controlador */

    @PostMapping("/agregar_equipo")
    public Equipo agregarEquipo(@RequestBody Equipo equipo) {
        return equipoServicio.agregarEquipo(equipo);
    }

    @GetMapping("/lista_equipos")
    public List<Equipo> verEquipos() {
        return equipoServicio.listaEquipos();
    }
}
