package com.curso.infraestructura.adaptador.salida.cliente;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * El prefijo "clientes" va en path=...: un @RequestMapping a nivel de interfaz
 * no esta soportado por las versiones actuales de Spring Cloud OpenFeign.
 */
@FeignClient(name = "ServicioClientes", path = "clientes")
public interface ClientesFeignClient {

    @GetMapping(path = "/{login}", produces = MediaType.APPLICATION_JSON_VALUE)
    ClienteRemotoDto buscar(@PathVariable("login") String login);

}
