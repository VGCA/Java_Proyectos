package com.bosonit.futbol_back_eureka.servicio;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bosonit.futbol_back_eureka.modelo.Liga;
import com.bosonit.futbol_back_eureka.repositorio.LigaRepo;


@Service
public class LigaServicio {

    @Autowired
    public LigaRepo ligaRepo;

    /**
     * VER TODOS LOS ligaS
     * 
     * @return
     */
    public List<Liga> listaligas() {
        return ligaRepo.findAll();
    }

    /**
     * AGREGAR UN NUEVO liga
     * 
     * @param liga
     * @return
     */
    public Liga agregarliga(Liga liga) {
        return ligaRepo.save(liga);
    }

    /**
     * BORRAR EQIUPO POR ID
     * 
     * @param id
     */
    public void borrarliga(int id) {
        Liga encontrado = this.buscarliga(id);
        ligaRepo.delete(encontrado);
    }

    /**
     * ENCUENTRA UN liga POR SU ID
     * 
     * @param id
     * @return
     */
    public Liga buscarliga(int id) {
        return ligaRepo.findById(id).orElse(null);
    }
}
