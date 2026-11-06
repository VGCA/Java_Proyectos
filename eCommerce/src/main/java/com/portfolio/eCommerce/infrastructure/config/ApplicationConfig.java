package com.portfolio.ecommerce.infrastructure.config;

import com.portfolio.ecommerce.application.port.in.OrdenUseCase;
import com.portfolio.ecommerce.application.port.in.ProductoUseCase;
import com.portfolio.ecommerce.application.port.in.UsuarioUseCase;
import com.portfolio.ecommerce.application.port.out.CodificadorPasswordPort;
import com.portfolio.ecommerce.application.port.out.ImagenStoragePort;
import com.portfolio.ecommerce.application.port.out.OrdenRepositoryPort;
import com.portfolio.ecommerce.application.port.out.ProductoRepositoryPort;
import com.portfolio.ecommerce.application.port.out.UsuarioRepositoryPort;
import com.portfolio.ecommerce.application.service.OrdenService;
import com.portfolio.ecommerce.application.service.ProductoService;
import com.portfolio.ecommerce.application.service.UsuarioService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Clock;

/**
 * Composition root: aquí (y solo aquí) se "enchufan" los adaptadores a los servicios de
 * aplicación. Los servicios no llevan @Service para no depender de Spring.
 */
@Configuration
public class ApplicationConfig {

    @Bean
    Clock clock() {
        return Clock.systemDefaultZone();
    }

    @Bean
    ProductoUseCase productoUseCase(ProductoRepositoryPort productos, UsuarioRepositoryPort usuarios,
                                    ImagenStoragePort imagenes) {
        return new ProductoService(productos, usuarios, imagenes);
    }

    @Bean
    UsuarioUseCase usuarioUseCase(UsuarioRepositoryPort usuarios, CodificadorPasswordPort codificador) {
        return new UsuarioService(usuarios, codificador);
    }

    @Bean
    OrdenUseCase ordenUseCase(OrdenRepositoryPort ordenes, UsuarioRepositoryPort usuarios, Clock reloj) {
        return new OrdenService(ordenes, usuarios, reloj);
    }
}
