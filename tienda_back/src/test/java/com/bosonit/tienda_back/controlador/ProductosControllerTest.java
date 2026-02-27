package com.bosonit.tienda_back.controlador;

import com.bosonit.tienda_back.modelo.Categoria;
import com.bosonit.tienda_back.modelo.Producto;
import com.bosonit.tienda_back.servicios.CategoriasServicio;
import com.bosonit.tienda_back.servicios.ProductosServicio;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ProductosController.class)
class ProductosControllerTest {

    private MockMvc mockMvc;
    private ProductosServicio productosServicio;
    private CategoriasServicio categoriasServicio;
    private ObjectMapper objectMapper;

    private static final String NOMBRE_CAMPO = "$.nombre";

    @BeforeEach
    void setup() {
        productosServicio = mock(ProductosServicio.class);
        categoriasServicio = mock(CategoriasServicio.class);
        ProductosController controller = new ProductosController(productosServicio, categoriasServicio);
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
        objectMapper = new ObjectMapper();
    }

    @Test
    void testListarProductos() throws Exception {
        Producto producto = new Producto(1, "Camisa", 19.99, "imagen.jpg", new Categoria(1, "Ropa"));
        List<Producto> productos = Arrays.asList(producto);

        when(productosServicio.buscarTodos()).thenReturn(productos);

        mockMvc.perform(get("/api/productos/listar"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].nombre").value("Camisa"));
    }

    @Test
    void testListarCategorias() throws Exception {
        Categoria categoria = new Categoria(1, "Electrónica");
        List<Categoria> categorias = Arrays.asList(categoria);

        when(categoriasServicio.buscarTodos()).thenReturn(categorias);

        mockMvc.perform(get("/api/productos/categorias"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].nombre").value("Electrónica"));
    }

    @Test
    void testAniadirProducto() throws Exception {
        Producto producto = new Producto(2, "Zapatos", 49.99, "zapatos.jpg", new Categoria(2, "Calzado"));

        when(productosServicio.guardar(any(Producto.class))).thenReturn(producto);

        mockMvc.perform(post("/api/productos/listar")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(producto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath(NOMBRE_CAMPO).value("Zapatos"));
    }

    @Test
    void testBuscarProducto() throws Exception {
        Producto producto = new Producto(3, "Gorra", 9.99, "gorra.jpg", new Categoria(3, "Accesorios"));

        when(productosServicio.buscarID(3)).thenReturn(producto);

        mockMvc.perform(get("/api/productos/listar/3"))
                .andExpect(status().isOk())
                .andExpect(jsonPath(NOMBRE_CAMPO).value("Gorra"));
    }

    @Test
    void testActualizarProducto() throws Exception {
        Producto original = new Producto(4, "Pantalón", 29.99, "pantalon.jpg", new Categoria(4, "Ropa"));
        Producto actualizado = new Producto(4, "Pantalón Jeans", 39.99, "jeans.jpg", new Categoria(4, "Ropa"));

        when(productosServicio.buscarID(4)).thenReturn(original);

        mockMvc.perform(put("/api/productos/listar/4")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(actualizado)))
                .andExpect(status().isOk())
                .andExpect(jsonPath(NOMBRE_CAMPO).value("Pantalón Jeans"))
                .andExpect(jsonPath("$.precio").value(39.99));
    }

    @Test
    void testBorrarProducto() throws Exception {
        doNothing().when(productosServicio).deleteProducto(5);

        mockMvc.perform(delete("/api/productos/listar/5"))
                .andExpect(status().isOk());

        verify(productosServicio, times(1)).deleteProducto(5);
    }
}
