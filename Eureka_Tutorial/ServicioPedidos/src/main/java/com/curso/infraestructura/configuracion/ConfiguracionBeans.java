package com.curso.infraestructura.configuracion;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.curso.aplicacion.puerto.entrada.GestionPedidos;
import com.curso.aplicacion.puerto.salida.ClientesRemotosPort;
import com.curso.aplicacion.puerto.salida.RepositorioClientesPort;
import com.curso.aplicacion.puerto.salida.RepositorioPedidosPort;
import com.curso.aplicacion.servicio.GestorPedidos;

/** Ensambla el nucleo con sus adaptadores. */
@Configuration
public class ConfiguracionBeans {

    @Bean
    public GestionPedidos gestionPedidos(RepositorioClientesPort repoClientes,
                                         RepositorioPedidosPort repoPedidos,
                                         ClientesRemotosPort clientesRemotos) {
        return new GestorPedidos(repoClientes, repoPedidos, clientesRemotos);
    }

}
