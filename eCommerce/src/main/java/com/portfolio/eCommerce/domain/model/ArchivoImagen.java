package com.portfolio.ecommerce.domain.model;

/** Imagen subida por el usuario, independiente de Spring ({@code MultipartFile}). */
public record ArchivoImagen(String nombreOriginal, byte[] contenido) {

    public static ArchivoImagen ninguno() {
        return new ArchivoImagen(null, new byte[0]);
    }

    public boolean esVacio() {
        return contenido == null || contenido.length == 0;
    }
}
