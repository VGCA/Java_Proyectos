package com.chat.websocketserver.infrastructure.adapter.in.websocket;

import com.chat.websocketserver.domain.model.Mensaje;

/**
 * DTO del adaptador de entrada: formato JSON que envía el cliente por STOMP.
 */
public record MensajeEntradaDto(String nombre, String contenido) {

    public Mensaje toDomain() {
        return new Mensaje(nombre, contenido);
    }
}
