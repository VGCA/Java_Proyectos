package com.curso.infraestructura.adaptador.entrada.rest;

public record ClienteDto(String login,
                         String nombre,
                         String direccionEnvio,
                         String direccionFacturacion,
                         String telefono) {
}
