package com.portfolio.ecommerce.infrastructure.adapter.out.storage;

import com.portfolio.ecommerce.application.port.out.ImagenStoragePort;
import com.portfolio.ecommerce.domain.model.ArchivoImagen;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.Files;
import java.nio.file.Path;

/** Guarda las imágenes en una carpeta del disco (app.storage.images-dir, por defecto "images"). */
@Component
public class FileSystemImagenStorageAdapter implements ImagenStoragePort {

    private static final Logger LOG = LoggerFactory.getLogger(FileSystemImagenStorageAdapter.class);

    private final Path directorio;

    public FileSystemImagenStorageAdapter(@Value("${app.storage.images-dir:images}") String directorio) {
        this.directorio = Path.of(directorio).toAbsolutePath().normalize();
    }

    @Override
    public String guardar(ArchivoImagen imagen) {
        String nombre = nombreSeguro(imagen.nombreOriginal());
        try {
            Files.createDirectories(directorio);
            Files.write(directorio.resolve(nombre), imagen.contenido());
            return nombre;
        } catch (IOException e) {
            throw new UncheckedIOException("No se pudo guardar la imagen " + nombre, e);
        }
    }

    @Override
    public void eliminar(String nombre) {
        try {
            Files.deleteIfExists(directorio.resolve(nombreSeguro(nombre)));
        } catch (IOException e) {
            LOG.warn("No se pudo borrar la imagen {}", nombre, e);
        }
    }

    /** Evita path traversal: se queda solo con el último tramo del nombre ("../../x.png" -> "x.png"). */
    private String nombreSeguro(String nombre) {
        if (nombre == null || nombre.isBlank()) {
            throw new IllegalArgumentException("La imagen no tiene nombre");
        }
        Path soloNombre = Path.of(nombre.replace('\\', '/')).getFileName();
        return soloNombre.toString();
    }
}
