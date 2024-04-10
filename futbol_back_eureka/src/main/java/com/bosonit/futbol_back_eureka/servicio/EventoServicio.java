package com.bosonit.futbol_back_eureka.servicio;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bosonit.futbol_back_eureka.modelo.Evento;
import com.bosonit.futbol_back_eureka.repositorio.EventoRepo;



@Service
public class EventoServicio {

    @Autowired
    public EventoRepo eventoRepo;
    
    /**
     * LISTAR TODOS LOS EVENTOS ACONTECIDOS
     * @return
     */
    public List<Evento> listarEventos(){
        return eventoRepo.findAll();
    }
}
