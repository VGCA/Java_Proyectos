package com.chat.websocketserver.application.service;

import com.chat.websocketserver.domain.model.Mensaje;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatNullPointerException;

class EnviarMensajeServiceTest {

    private final List<Mensaje> publicados = new ArrayList<>();
    private final EnviarMensajeService service = new EnviarMensajeService(publicados::add);

    @Test
    void shouldPublishMensajeWithSameData() {
        service.enviar(new Mensaje("Juan", "Hola Mundo"));

        assertThat(publicados).containsExactly(new Mensaje("Juan", "Hola Mundo"));
    }

    @Test
    void shouldRejectNullMensaje() {
        assertThatNullPointerException().isThrownBy(() -> service.enviar(null));
        assertThat(publicados).isEmpty();
    }
}
