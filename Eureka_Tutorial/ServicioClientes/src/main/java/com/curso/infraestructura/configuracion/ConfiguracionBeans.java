package com.curso.infraestructura.configuracion;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.curso.aplicacion.puerto.entrada.GestionClientes;
import com.curso.aplicacion.puerto.salida.RepositorioClientesPort;
import com.curso.aplicacion.servicio.GestorClientes;

/**
 * Ensambla el nucleo (sin anotaciones de Spring) con sus adaptadores.
 */
@Configuration
public class ConfiguracionBeans {

    @Bean
    public GestionClientes gestionClientes(RepositorioClientesPort repositorio) {
        return new GestorClientes(repositorio);
    }

}
