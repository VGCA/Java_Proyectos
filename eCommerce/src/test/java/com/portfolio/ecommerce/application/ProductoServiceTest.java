package com.portfolio.ecommerce.application;

import com.portfolio.ecommerce.application.port.in.ProductoUseCase.DatosProducto;
import com.portfolio.ecommerce.application.service.ProductoService;
import com.portfolio.ecommerce.domain.exception.RecursoNoEncontradoException;
import com.portfolio.ecommerce.domain.model.ArchivoImagen;
import com.portfolio.ecommerce.domain.model.Producto;
import com.portfolio.ecommerce.domain.model.Usuario;
import com.portfolio.ecommerce.support.Fakes.ImagenStorageEnMemoria;
import com.portfolio.ecommerce.support.Fakes.ProductoRepoEnMemoria;
import com.portfolio.ecommerce.support.Fakes.UsuarioRepoEnMemoria;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ProductoServiceTest {

    private ProductoRepoEnMemoria productos;
    private ImagenStorageEnMemoria imagenes;
    private ProductoService service;
    private Integer idAdmin;

    @BeforeEach
    void preparar() {
        productos = new ProductoRepoEnMemoria();
        imagenes = new ImagenStorageEnMemoria();
        UsuarioRepoEnMemoria usuarios = new UsuarioRepoEnMemoria();
        Usuario admin = new Usuario();
        admin.setTipo(Usuario.ROL_ADMIN);
        idAdmin = usuarios.guardar(admin).getId();
        service = new ProductoService(productos, usuarios, imagenes);
    }

    private DatosProducto datos(String nombre) {
        return new DatosProducto(nombre, "desc", 9.99, 3);
    }

    @Test
    void crearSinImagenUsaLaImagenPorDefecto() {
        Producto p = service.crear(datos("Teclado"), idAdmin, ArchivoImagen.ninguno());
        assertEquals(Producto.IMAGEN_POR_DEFECTO, p.getImagen());
        assertEquals(idAdmin, p.getUsuario().getId());
        assertTrue(imagenes.guardadas.isEmpty());
    }

    @Test
    void crearConImagenLaGuardaEnElAlmacenamiento() {
        Producto p = service.crear(datos("Ratón"), idAdmin, new ArchivoImagen("raton.png", new byte[]{1, 2}));
        assertEquals("raton.png", p.getImagen());
        assertEquals(1, imagenes.guardadas.size());
    }

    @Test
    void actualizarSinImagenConservaLaAnterior() {
        Producto creado = service.crear(datos("A"), idAdmin, new ArchivoImagen("a.png", new byte[]{1}));
        Producto p = service.actualizar(creado.getId(), datos("B"), ArchivoImagen.ninguno());
        assertEquals("B", p.getNombre());
        assertEquals("a.png", p.getImagen());
        assertEquals(idAdmin, p.getUsuario().getId());
        assertTrue(imagenes.eliminadas.isEmpty());
    }

    @Test
    void actualizarConImagenNuevaBorraLaVieja() {
        Producto creado = service.crear(datos("A"), idAdmin, new ArchivoImagen("a.png", new byte[]{1}));
        Producto p = service.actualizar(creado.getId(), datos("A"), new ArchivoImagen("b.png", new byte[]{2}));
        assertEquals("b.png", p.getImagen());
        assertEquals("a.png", imagenes.eliminadas.get(0));
    }

    @Test
    void eliminarNoBorraLaImagenPorDefecto() {
        Producto creado = service.crear(datos("A"), idAdmin, ArchivoImagen.ninguno());
        service.eliminar(creado.getId());
        assertTrue(imagenes.eliminadas.isEmpty());
        assertThrows(RecursoNoEncontradoException.class, () -> service.obtener(creado.getId()));
    }

    @Test
    void obtenerInexistenteLanzaExcepcionDeDominio() {
        assertThrows(RecursoNoEncontradoException.class, () -> service.obtener(99));
    }
}
