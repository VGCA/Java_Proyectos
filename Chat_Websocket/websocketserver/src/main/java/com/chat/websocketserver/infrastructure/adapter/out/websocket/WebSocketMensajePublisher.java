package com.chat.websocketserver.infrastructure.adapter.out.websocket;

import com.chat.websocketserver.application.port.out.PublicarMensajePort;
import com.chat.websocketserver.domain.model.Mensaje;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

/**
 * Adaptador de salida: publica el mensaje en el topic /tema/mensajes del broker STOMP.
 */
@Component
public class WebSocketMensajePublisher implements PublicarMensajePort {

    static final String DESTINO = "/tema/mensajes";

    private final SimpMessagingTemplate messagingTemplate;

    public WebSocketMensajePublisher(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

    @Override
    public void publicar(Mensaje mensaje) {
        messagingTemplate.convertAndSend(DESTINO, MensajeSalidaDto.fromDomain(mensaje));
    }
}
