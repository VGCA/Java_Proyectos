package com.curso.dominio.modelo;

public record Cliente(String login,
                      String nombre,
                      String direccionEnvio,
                      String direccionFacturacion,
                      String telefono) {
}
