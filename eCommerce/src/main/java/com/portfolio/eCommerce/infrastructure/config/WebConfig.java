package com.portfolio.ecommerce.infrastructure.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.nio.file.Path;

/** Sirve /images/** desde la carpeta de imágenes del disco. */
@Configuration
public class WebConfig implements WebMvcConfigurer {

    private final String directorioImagenes;

    public WebConfig(@Value("${app.storage.images-dir:images}") String directorioImagenes) {
        this.directorioImagenes = directorioImagenes;
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        String ubicacion = Path.of(directorioImagenes).toAbsolutePath().normalize().toUri().toString();
        if (!ubicacion.endsWith("/")) {
            ubicacion += "/";
        }
        registry.addResourceHandler("/images/**").addResourceLocations(ubicacion);
    }
}
