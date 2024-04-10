package com.example.demo.entidades;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class DTO {

    private long cuentaOrigenId;
    private long cuentaDestinoId;
    private BigDecimal monto;
    private long bancoId;
}
