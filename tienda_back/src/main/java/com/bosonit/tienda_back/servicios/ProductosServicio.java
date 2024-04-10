package com.bosonit.tienda_back.servicios;

import java.util.List;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bosonit.tienda_back.dao.ProductosDao;
import com.bosonit.tienda_back.modelo.Producto;

@Service
@AllArgsConstructor
public class ProductosServicio {

    private ProductosDao productosRepo;

    /**
     * GUARDAR UN PRODUCTO NUEVO
     * @param producto
     * @return
     */
    public Producto guardar(Producto producto) {
        return productosRepo.save(producto);
    }

    /**
     * BUSCAR POR ID UN PRODUCTO
     * @param id
     * @return
     */
    public Producto buscarID(int id) {
        return productosRepo.findById(id).orElse(null);
    }

    /**
     * BUSCAR TODOS LOS PRODUCTOS
     * @return
     */
    public List<Producto> buscarTodos() {
        return productosRepo.findAll();
    }

    /**
     * BORRAR PRODUCTO POR ID
     * @param id
     */
    public void deleteProducto(int id) {
        productosRepo.deleteById(id);
    }
}
