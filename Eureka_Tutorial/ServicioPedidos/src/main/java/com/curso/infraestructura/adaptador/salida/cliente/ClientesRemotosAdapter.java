package com.curso.infraestructura.adaptador.salida.cliente;

import java.util.Optional;

import org.springframework.stereotype.Component;

import com.curso.aplicacion.puerto.salida.ClientesRemotosPort;
import com.curso.dominio.modelo.Cliente;

import feign.FeignException;

@Component
public class ClientesRemotosAdapter implements ClientesRemotosPort {

    private final ClientesFeignClient feign;

    public ClientesRemotosAdapter(ClientesFeignClient feign) {
        this.feign = feign;
    }

    @Override
    public Optional<Cliente> buscarPorLogin(String login) {
        try {
            ClienteRemotoDto d = feign.buscar(login);
            return Optional.of(new Cliente(d.login(), d.nombre(), d.direccionEnvio(),
                    d.direccionFacturacion(), d.telefono()));
        } catch (FeignException.NotFound e) {
            return Optional.empty();
        }
    }

}
