package com.portfolio.eCommerce.servicio;

import com.portfolio.eCommerce.modelo.Orden;
import com.portfolio.eCommerce.modelo.Usuario;
import com.portfolio.eCommerce.repositorio.OrdenRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class OrdenServicioImp implements OrdenServicio {

    @Autowired
    private OrdenRepo ordenRepo;

    @Override
    public Orden save(Orden orden) {
        return ordenRepo.save(orden);
    }

    @Override
    public List<Orden> findAll() {
        return ordenRepo.findAll();
    }

    /**
     * GENERA NUMERO DE SERIE DE LA ORDEN
     * @return
     */
    public String generarNumeroOrden() {

        int numero = 0;
        String numeroConcatenado = "";
        List<Orden> ordenes = this.findAll();
        List<Integer> numeros = new ArrayList<Integer>();

        ordenes.stream().forEach(orden -> numeros.add(Integer.parseInt(orden.getNumero())));

        if (ordenes.isEmpty()) {
            numero = 1;
        } else {
            numero = numeros.stream().max(Integer::compare).get();
            numero++;
        }

        if (numero < 10) {
            numeroConcatenado = "000000000" + String.valueOf(numero);
        } else if (numero < 100) {
            numeroConcatenado = "00000000" + String.valueOf(numero);
        } else if (numero < 1000) {
            numeroConcatenado = "0000000" + String.valueOf(numero);
        }

        return numeroConcatenado;
    }

    @Override
    public List<Orden> findByUsuario(Usuario usuario) {
        return ordenRepo.findByUsuario(usuario);
    }

    @Override
    public Optional<Orden> findById(Integer id) {
        return ordenRepo.findById(id);
    }
}
