package com.portfolio.ecommerce.application.port.out;

import com.portfolio.ecommerce.domain.model.ArchivoImagen;

public interface ImagenStoragePort {

    /** Guarda la imagen y devuelve el nombre con el que quedó almacenada. */
    String guardar(ArchivoImagen imagen);

    void eliminar(String nombre);
}
