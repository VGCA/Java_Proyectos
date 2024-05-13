package com.example.demo.repositorios;

import com.example.demo.entidades.Banco;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@DataJpaTest
class BancoRepoTest {

    private Banco banco;
    @Mock
    private BancoRepo bancoRepo;
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
    void findAllTest() {
        when(bancoRepo.findAll()).thenReturn(List.of(banco));
        List<Banco> allBancos = bancoRepo.findAll();
        assertEquals(1, allBancos.size());
    }
}