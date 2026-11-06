package com.portfolio.ecommerce.support;

import com.portfolio.ecommerce.application.port.out.CodificadorPasswordPort;
import com.portfolio.ecommerce.application.port.out.ImagenStoragePort;
import com.portfolio.ecommerce.application.port.out.OrdenRepositoryPort;
import com.portfolio.ecommerce.application.port.out.ProductoRepositoryPort;
import com.portfolio.ecommerce.application.port.out.UsuarioRepositoryPort;
import com.portfolio.ecommerce.domain.model.ArchivoImagen;
import com.portfolio.ecommerce.domain.model.Orden;
import com.portfolio.ecommerce.domain.model.Producto;
import com.portfolio.ecommerce.domain.model.Usuario;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * Adaptadores falsos en memoria. Es la gran ventaja de la arquitectura hexagonal: los casos
 * de uso se prueban sin base de datos, sin Spring y sin Mockito.
 */
public final class Fakes {

    private Fakes() {
    }

    public static class UsuarioRepoEnMemoria implements UsuarioRepositoryPort {
        private final Map<Integer, Usuario> datos = new LinkedHashMap<>();
        private int secuencia = 0;

        @Override
        public Usuario guardar(Usuario u) {
            if (u.getId() == null) {
                u.setId(++secuencia);
            }
            datos.put(u.getId(), u);
            return u;
        }

        @Override
        public Optional<Usuario> buscarPorId(Integer id) {
            return Optional.ofNullable(datos.get(id));
        }

        @Override
        public Optional<Usuario> buscarPorEmail(String email) {
            return datos.values().stream().filter(u -> email.equals(u.getEmail())).findFirst();
        }

        @Override
        public List<Usuario> buscarTodos() {
            return new ArrayList<>(datos.values());
        }
    }

    public static class ProductoRepoEnMemoria implements ProductoRepositoryPort {
        private final Map<Integer, Producto> datos = new LinkedHashMap<>();
        private int secuencia = 0;

        @Override
        public Producto guardar(Producto p) {
            if (p.getId() == null) {
                p.setId(++secuencia);
            }
            datos.put(p.getId(), p);
            return p;
        }

        @Override
        public Optional<Producto> buscarPorId(Integer id) {
            return Optional.ofNullable(datos.get(id));
        }

        @Override
        public List<Producto> buscarTodos() {
            return new ArrayList<>(datos.values());
        }

        @Override
        public void eliminarPorId(Integer id) {
            datos.remove(id);
        }
    }

    public static class OrdenRepoEnMemoria implements OrdenRepositoryPort {
        private final Map<Integer, Orden> datos = new LinkedHashMap<>();
        private int secuencia = 0;

        @Override
        public Orden guardar(Orden o) {
            if (o.getId() == null) {
                o.setId(++secuencia);
            }
            datos.put(o.getId(), o);
            return o;
        }

        @Override
        public Optional<Orden> buscarPorId(Integer id) {
            return Optional.ofNullable(datos.get(id));
        }

        @Override
        public List<Orden> buscarTodas() {
            return new ArrayList<>(datos.values());
        }

        @Override
        public List<Orden> buscarPorUsuario(Integer idUsuario) {
            return datos.values().stream()
                    .filter(o -> o.getUsuario() != null && idUsuario.equals(o.getUsuario().getId()))
                    .toList();
        }
    }

    public static class ImagenStorageEnMemoria implements ImagenStoragePort {
        public final List<String> guardadas = new ArrayList<>();
        public final List<String> eliminadas = new ArrayList<>();

        @Override
        public String guardar(ArchivoImagen imagen) {
            guardadas.add(imagen.nombreOriginal());
            return imagen.nombreOriginal();
        }

        @Override
        public void eliminar(String nombre) {
            eliminadas.add(nombre);
        }
    }

    public static class CodificadorFalso implements CodificadorPasswordPort {
        @Override
        public String codificar(String passwordPlano) {
            return "hash(" + passwordPlano + ")";
        }
    }
}
