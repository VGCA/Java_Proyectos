package com.bosonit.inventario;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import com.bosonit.inventario.categoria.Categoria;
import com.bosonit.inventario.repositorio.CategoriaRepositorio;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
class CategoriaRepoTest {


    private final CategoriaRepositorio categoriaRepo;

    @Autowired
    public CategoriaRepoTest(CategoriaRepositorio categoriaRepo) {
        this.categoriaRepo = categoriaRepo;
    }

    @Test
    void testCrearCategoria() {
        Categoria nuevaCategoria = categoriaRepo.save(new Categoria("Electronicos"));
        assertTrue(nuevaCategoria.getId() > 0);
    }
}
