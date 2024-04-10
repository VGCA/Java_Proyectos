package com.bosonit.futbol_back_eureka.servicio;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bosonit.futbol_back_eureka.modelo.Equipo;
import com.bosonit.futbol_back_eureka.repositorio.EquipoRepo;



@Service
public class EquipoServicio {

    @Autowired
    public EquipoRepo equipoRepo;

    /**
     * VER TODOS LOS EQUIPOS
     * 
     * @return
     */
    public List<Equipo> listaEquipos() {
        return equipoRepo.findAll();
    }

    /**
     * AGREGAR UN NUEVO EQUIPO
     * 
     * @param equipo
     * @return
     */
    public Equipo agregarEquipo(Equipo equipo) {
        return equipoRepo.save(equipo);
    }

    /**
     * BORRAR EQIUPO POR ID
     * 
     * @param id
     */
    public void borrarEquipo(int id) {
        Equipo encontrado = this.buscarEquipo(id);
        equipoRepo.delete(encontrado);
    }

    /**
     * ENCUENTRA UN equipo POR SU ID
     * 
     * @param id
     * @return
     */
    public Equipo buscarEquipo(int id) {
        return equipoRepo.findById(id).orElse(null);
    }

    /**
     * ACTUALIZA UN equipo DISPONIBLE
     * 
     * @param id
     * @param equipo
     * @return
     */
    public Equipo actualizarequipo(int id, Equipo equipo) {
        Equipo encontrado = this.buscarEquipo(id);
        encontrado.setTitulo(equipo.getTitulo());
        return encontrado;
    }
}
