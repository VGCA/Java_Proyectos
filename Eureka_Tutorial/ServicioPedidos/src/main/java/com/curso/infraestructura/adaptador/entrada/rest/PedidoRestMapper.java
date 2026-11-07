package com.curso.infraestructura.adaptador.entrada.rest;

import com.curso.dominio.modelo.Cliente;
import com.curso.dominio.modelo.Pedido;

final class PedidoRestMapper {

    private PedidoRestMapper() {
    }

    static PedidoDto aDto(Pedido p) {
        Cliente c = p.cliente();
        ClienteDto clienteDto = new ClienteDto(c.login(), c.nombre(), c.direccionEnvio(),
                c.direccionFacturacion(), c.telefono());
        return new PedidoDto(p.id(), p.codigo(), p.fecha(), p.estado(), clienteDto);
    }

    static Pedido aDominio(PedidoDto d) {
        ClienteDto c = d.cliente();
        Cliente cliente = new Cliente(c.login(), c.nombre(), c.direccionEnvio(),
                c.direccionFacturacion(), c.telefono());
        return new Pedido(d.id(), d.codigo(), d.fecha(), d.estado(), cliente, null);
    }

}
