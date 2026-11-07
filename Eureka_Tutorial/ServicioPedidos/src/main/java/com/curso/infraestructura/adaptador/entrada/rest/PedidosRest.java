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

import com.curso.aplicacion.puerto.entrada.GestionPedidos;
import com.curso.dominio.modelo.Pedido;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/pedidos")
public class PedidosRest {

    private final GestionPedidos gestionPedidos;

    public PedidosRest(GestionPedidos gestionPedidos) {
        this.gestionPedidos = gestionPedidos;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE,
                 produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PedidoDto> insertar(@Valid @RequestBody PedidoDto pedidoDto) {
        Pedido guardado = gestionPedidos.altaPedido(PedidoRestMapper.aDominio(pedidoDto));
        return new ResponseEntity<>(PedidoRestMapper.aDto(guardado), HttpStatus.OK);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<PedidoDto> listar() {
        return gestionPedidos.listarPedidos().stream()
                .map(PedidoRestMapper::aDto)
                .toList();
    }

    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PedidoDto> buscar(@PathVariable("id") Integer id) {
        return new ResponseEntity<>(PedidoRestMapper.aDto(gestionPedidos.buscarPedido(id)), HttpStatus.OK);
    }

    @PutMapping(path = "/{id}",
                consumes = MediaType.APPLICATION_JSON_VALUE,
                produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PedidoDto> modificar(@PathVariable("id") Integer idPedido,
                                               @Valid @RequestBody PedidoDto pedidoDto) {
        // Nos fiamos solo del id que viene en la url, no en el pedido del body
        Pedido pedido = PedidoRestMapper.aDominio(pedidoDto).conId(idPedido);
        gestionPedidos.modificarPedido(pedido);
        return new ResponseEntity<>(PedidoRestMapper.aDto(pedido), HttpStatus.OK);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<String> borrar(@PathVariable("id") Integer idPedido) {
        gestionPedidos.borrarPedido(idPedido);
        return new ResponseEntity<>("Pedido borrado", HttpStatus.OK);
    }

}
