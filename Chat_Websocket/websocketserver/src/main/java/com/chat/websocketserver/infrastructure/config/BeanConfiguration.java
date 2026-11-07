package com.chat.websocketserver.infrastructure.config;

import com.chat.websocketserver.application.port.in.EnviarMensajeUseCase;
import com.chat.websocketserver.application.port.out.PublicarMensajePort;
import com.chat.websocketserver.application.service.EnviarMensajeService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Cableado de la capa de aplicación: aquí (y solo aquí) se une el núcleo con Spring.
 */
@Configuration
public class BeanConfiguration {

    @Bean
    public EnviarMensajeUseCase enviarMensajeUseCase(PublicarMensajePort publicarMensajePort) {
        return new EnviarMensajeService(publicarMensajePort);
    }
}
