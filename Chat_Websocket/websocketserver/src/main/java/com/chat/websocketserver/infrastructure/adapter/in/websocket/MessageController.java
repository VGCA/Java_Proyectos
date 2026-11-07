package com.chat.websocketserver.infrastructure.adapter.in.websocket;

import com.chat.websocketserver.application.port.in.EnviarMensajeUseCase;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;

/**
 * Adaptador de entrada: recibe mensajes STOMP en /app/envio y delega en el caso de uso.
 */
@Controller
public class MessageController {

    private final EnviarMensajeUseCase enviarMensajeUseCase;

    public MessageController(EnviarMensajeUseCase enviarMensajeUseCase) {
        this.enviarMensajeUseCase = enviarMensajeUseCase;
    }

    @MessageMapping("/envio")
    public void envio(MensajeEntradaDto mensaje) {
        enviarMensajeUseCase.enviar(mensaje.toDomain());
    }
}
