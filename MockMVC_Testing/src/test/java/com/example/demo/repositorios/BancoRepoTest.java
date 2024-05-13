package com.example.demo.repositorios;

import com.example.demo.entidades.Banco;
import com.example.demo.servicio.CuentaServicioImp;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.awaitility.Awaitility.given;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class BancoRepoTest {

    private Banco banco;
    @Mock
    private BancoRepo bancoRepo;
    @InjectMocks
    private CuentaServicioImp cuentaServicioImp;
    private Banco saveBanco;

    @BeforeEach
    void setUp() {
        banco = new Banco(1L, "firstBanco", 1);
    }

    @Test
    void testingSaveMethod() {
        when(bancoRepo.save(banco)).thenReturn(banco);
        saveBanco = bancoRepo.save(banco);
        assertThat(saveBanco).isNotNull();
    }

    @Test
    void findAllTest(){
        when(bancoRepo.findAll()).thenReturn(List.of(banco));
        List<Banco> allBancos = bancoRepo.findAll();
        assertEquals(1,allBancos.size());
    }
}