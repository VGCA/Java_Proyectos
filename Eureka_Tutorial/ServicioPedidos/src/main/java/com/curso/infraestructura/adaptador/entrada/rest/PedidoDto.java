package com.curso.infraestructura.adaptador.entrada.rest;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public record PedidoDto(Integer id,
                        @Size(min = 1, max = 10) String codigo,
                        String fecha,
                        @NotEmpty String estado,
                        ClienteDto cliente) {
}
