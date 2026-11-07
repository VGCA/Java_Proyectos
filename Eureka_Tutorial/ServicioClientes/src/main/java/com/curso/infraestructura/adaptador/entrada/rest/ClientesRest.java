package com.curso.infraestructura.adaptador.entrada.rest;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.curso.aplicacion.puerto.entrada.GestionClientes;
import com.curso.dominio.modelo.Cliente;

import jakarta.validation.Valid;

@RestController
@RequestMapping("clientes")
public class ClientesRest {

    private final GestionClientes gestionClientes;

    public ClientesRest(GestionClientes gestionClientes) {
        this.gestionClientes = gestionClientes;
    }

    @GetMapping(path = "/{login}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ClienteDto> buscar(@PathVariable("login") String login) {
        Cliente cliente = gestionClientes.buscarCliente(login);
        return new ResponseEntity<>(ClienteRestMapper.aDto(cliente), HttpStatus.OK);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<ClienteDto> listar() {
        return gestionClientes.listarClientes().stream()
                .map(ClienteRestMapper::aDto)
                .toList();
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE,
                 produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ClienteDto> insertar(@Valid @RequestBody ClienteDto clienteDto) {
        Cliente guardado = gestionClientes.altaCliente(ClienteRestMapper.aDominio(clienteDto));
        return new ResponseEntity<>(ClienteRestMapper.aDto(guardado), HttpStatus.OK);
    }

    @PutMapping(path = "/{login}",
                consumes = MediaType.APPLICATION_JSON_VALUE,
                produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ClienteDto> modificar(@PathVariable("login") String login,
                                                @Valid @RequestBody ClienteDto clienteDto) {
        // Nos fiamos solo del login que viene en la url, no en el del body
        Cliente cliente = ClienteRestMapper.aDominio(clienteDto).conLogin(login);
        Cliente guardado = gestionClientes.modificarCliente(cliente);
        return new ResponseEntity<>(ClienteRestMapper.aDto(guardado), HttpStatus.OK);
    }

    @DeleteMapping(path = "/{login}")
    public ResponseEntity<String> borrar(@PathVariable("login") String login) {
        gestionClientes.bajaCliente(login);
        return new ResponseEntity<>("Cliente borrado", HttpStatus.OK);
    }

}
