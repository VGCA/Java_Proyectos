package com.example.demo.servicio;

import com.example.demo.entidades.Cuenta;

import java.math.BigDecimal;
import java.util.List;

public interface CuentaServicio {

    public List<Cuenta> listarCuentas();

    public Cuenta buscarCuentaID(Long id);

    public Cuenta guardarCuentaID(Cuenta cuenta);

    public int revisarTransferencias(Long bancoId);

    public BigDecimal revisarSaldo(Long cuentaId);

    public void transferirDinero(Long numeroCuentaOrigen, Long numeroCuentaDestino, BigDecimal monto, Long bancoId);
}
