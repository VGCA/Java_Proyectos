package com.chat.websocketserver.infrastructure.adapter.in.websocket;

import com.chat.websocketserver.domain.model.Mensaje;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class MessageControllerTest {

    private final List<Mensaje> recibidos = new ArrayList<>();
    private final MessageController controller = new MessageController(recibidos::add);

    @Test
    void shouldDelegateMensajeToUseCase() {
        controller.envio(new MensajeEntradaDto("Juan", "Hola Mundo"));

        assertThat(recibidos).containsExactly(new Mensaje("Juan", "Hola Mundo"));
    }
}
