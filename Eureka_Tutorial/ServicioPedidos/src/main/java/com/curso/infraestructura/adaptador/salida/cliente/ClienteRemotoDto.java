package com.curso.infraestructura.adaptador.salida.cliente;

/** Forma en que ServicioClientes devuelve un cliente (los campos que sobran se ignoran). */
public record ClienteRemotoDto(String login,
                               String nombre,
                               String direccionEnvio,
                               String direccionFacturacion,
                               String telefono) {
}
