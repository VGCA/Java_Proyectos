package com.curso.infraestructura.adaptador.entrada.rest;

import jakarta.validation.constraints.NotEmpty;

public record ClienteDto(@NotEmpty String login,
                         String preferencias,
                         @NotEmpty String nombre,
                         String direccion,
                         @NotEmpty String direccionEnvio,
                         String direccionFacturacion,
                         @NotEmpty String telefono) {
}
