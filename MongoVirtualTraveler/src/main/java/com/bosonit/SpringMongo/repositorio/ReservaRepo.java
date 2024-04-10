package com.bosonit.SpringMongo.repositorio;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

import com.bosonit.SpringMongo.modelo.Reserva;

@Repository
public class ReservaRepo {

    @Autowired
    private MongoTemplate mongoTemplate;

    /**
     * FUNCION QUE HACE UNA RESERVA
     * 
     * @param reserva en formato json
     * @return la reserva creada
     */
    public Reserva hacerReserva(Reserva reserva) {
        Reserva encontrada = mongoTemplate.findById(reserva.getId(), Reserva.class);
        Reserva nuevaReserva = null;
        if (encontrada == null) {
            nuevaReserva = mongoTemplate.save(reserva);
        }
        return nuevaReserva;
    }

    /**
     * METODO QUE ELIMINA UNA RESERVA DE LA BDD
     * 
     * @param reserva para eliminar
     */
    public void eliminarReserva(Reserva reserva) {
        mongoTemplate.remove(reserva);
    }

    /**
     * FUNCION QUE ACTUALIZA UNA RESERVA
     * 
     * @param reserva para actualizar
     * @return reserva actualizada
     */
    public Reserva updateReserva(Reserva reserva) {
        return mongoTemplate.save(reserva);
    }

    /**
     * FUNCION QUE MUESTRA TODAS LAS RESERVAS
     * 
     * @return todas las reservas
     */
    public List<Reserva> getAllReservas() {
        return mongoTemplate.findAll(Reserva.class);
    }

    /**
     * METODO QUE ELIMINA TODOS LOS REGISTROS
     */
    public void borrarTodo(){
        mongoTemplate.dropCollection(Reserva.class);
    }

}
