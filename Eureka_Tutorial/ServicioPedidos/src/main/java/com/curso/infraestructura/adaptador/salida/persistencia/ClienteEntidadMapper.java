package com.curso.infraestructura.adaptador.salida.persistencia;

import com.curso.dominio.modelo.Cliente;

final class ClienteEntidadMapper {

    private ClienteEntidadMapper() {
    }

    static Cliente aDominio(ClienteEntidad e) {
        return new Cliente(e.getLogin(), e.getNombre(), e.getDireccionEnvio(),
                e.getDireccionFacturacion(), e.getTelefono());
    }

    static ClienteEntidad aEntidad(Cliente c) {
        ClienteEntidad e = new ClienteEntidad();
        e.setLogin(c.login());
        e.setNombre(c.nombre());
        e.setDireccionEnvio(c.direccionEnvio());
        e.setDireccionFacturacion(c.direccionFacturacion());
        e.setTelefono(c.telefono());
        return e;
    }

}
