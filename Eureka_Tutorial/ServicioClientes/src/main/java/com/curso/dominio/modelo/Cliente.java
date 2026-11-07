package com.curso.dominio.modelo;

/**
 * Cliente del dominio. No depende de ningun framework.
 */
public record Cliente(String login,
                      String password,
                      String preferencias,
                      String nombre,
                      String direccion,
                      String direccionEnvio,
                      String direccionFacturacion,
                      String telefono) {

    /** Devuelve una copia del cliente con otro login. */
    public Cliente conLogin(String nuevoLogin) {
        return new Cliente(nuevoLogin, password, preferencias, nombre, direccion,
                direccionEnvio, direccionFacturacion, telefono);
    }

}
