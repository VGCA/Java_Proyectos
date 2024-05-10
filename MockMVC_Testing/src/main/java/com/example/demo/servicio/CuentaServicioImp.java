package com.example.demo.servicio;

import com.example.demo.entidades.Banco;
import com.example.demo.entidades.Cuenta;
import com.example.demo.repositorios.BancoRepo;
import com.example.demo.repositorios.CuentaRepo;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.List;

@Service
@AllArgsConstructor
public class CuentaServicioImp implements CuentaServicio{

    private CuentaRepo cuentaRepo;
    private BancoRepo bancoRepo;

    @Override
    @Transactional
    public List<Cuenta> listarCuentas() {
        return cuentaRepo.findAll();
    }

    @Override
    @Transactional
    public Cuenta buscarCuentaID(Long id) {
        return cuentaRepo.findById(id).orElseThrow();
    }

    @Override
    @Transactional
    public Cuenta guardarCuentaID(Cuenta cuenta) {
        return cuentaRepo.save(cuenta);
    }

    @Override
    @Transactional
    public int revisarTransferencias(Long bancoId) {
        Banco banco = bancoRepo.findById(bancoId).orElseThrow();
        return banco.getTotalTransferencias();
    }

    @Override
    @Transactional
    public BigDecimal revisarSaldo(Long cuentaId) {
        Cuenta cuenta = cuentaRepo.findById(cuentaId).orElseThrow();
        return cuenta.getSaldo();
    }

    @Override
    public void transferirDinero(Long numeroCuentaOrigen, Long numeroCuentaDestino, BigDecimal monto, Long bancoId) {
        Cuenta cuentaOrigen = cuentaRepo.findById(numeroCuentaOrigen).orElseThrow();
        cuentaOrigen.realizarDebito(monto);
        cuentaRepo.save(cuentaOrigen);

        Cuenta cuentaDestino = cuentaRepo.findById(numeroCuentaDestino).orElseThrow();
        cuentaDestino.realizarCredito(monto);
        cuentaRepo.save(cuentaDestino);

        Banco banco = bancoRepo.findById(bancoId).orElseThrow();
        int totalTransferencias = banco.getTotalTransferencias();
        banco.setTotalTransferencias(++totalTransferencias);
        bancoRepo.save(banco);
    }
}
