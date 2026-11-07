package com.curso.infraestructura.adaptador.entrada.rest;

import com.curso.dominio.modelo.Cliente;

final class ClienteRestMapper {

    private ClienteRestMapper() {
    }

    static ClienteDto aDto(Cliente c) {
        return new ClienteDto(c.login(), c.preferencias(), c.nombre(), c.direccion(),
                c.direccionEnvio(), c.direccionFacturacion(), c.telefono());
    }

    /** El DTO no transporta la password, por eso el dominio la recibe a null. */
    static Cliente aDominio(ClienteDto d) {
        return new Cliente(d.login(), null, d.preferencias(), d.nombre(), d.direccion(),
                d.direccionEnvio(), d.direccionFacturacion(), d.telefono());
    }

}
