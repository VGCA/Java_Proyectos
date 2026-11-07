package com.chat.websocketserver.infrastructure.adapter.out.websocket;

import com.chat.websocketserver.domain.model.Mensaje;
import org.junit.jupiter.api.Test;
import org.springframework.messaging.Message;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessagingTemplate;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class WebSocketMensajePublisherTest {

    private final List<Message<?>> enviados = new ArrayList<>();
    private final SimpMessagingTemplate template = new SimpMessagingTemplate((message, timeout) -> {
        enviados.add(message);
        return true;
    });
    private final WebSocketMensajePublisher publisher = new WebSocketMensajePublisher(template);

    @Test
    void shouldSendMensajeToTopic() {
        publisher.publicar(new Mensaje("Juan", "Hola Mundo"));

        assertThat(enviados).hasSize(1);
        Message<?> enviado = enviados.get(0);
        assertThat(enviado.getHeaders().get(SimpMessageHeaderAccessor.DESTINATION_HEADER))
                .isEqualTo("/tema/mensajes");
        assertThat(enviado.getPayload()).isEqualTo(new MensajeSalidaDto("Juan", "Hola Mundo"));
    }
}
