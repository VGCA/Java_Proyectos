package com.chat.websocketserver.application.service;

import com.chat.websocketserver.application.port.in.EnviarMensajeUseCase;
import com.chat.websocketserver.application.port.out.PublicarMensajePort;
import com.chat.websocketserver.domain.model.Mensaje;

import java.util.Objects;

/**
 * Implementación del caso de uso. Java puro: se instancia desde la configuración
 * de infraestructura, por lo que no necesita anotaciones de Spring.
 */
public class EnviarMensajeService implements EnviarMensajeUseCase {

    private final PublicarMensajePort publicarMensajePort;

    public EnviarMensajeService(PublicarMensajePort publicarMensajePort) {
        this.publicarMensajePort = Objects.requireNonNull(publicarMensajePort);
    }

    @Override
    public void enviar(Mensaje mensaje) {
        Objects.requireNonNull(mensaje, "El mensaje no puede ser nulo");
        publicarMensajePort.publicar(new Mensaje(mensaje.nombre(), mensaje.contenido()));
    }
}
