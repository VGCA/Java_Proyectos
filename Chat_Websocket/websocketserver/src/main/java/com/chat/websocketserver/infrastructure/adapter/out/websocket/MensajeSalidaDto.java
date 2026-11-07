package com.chat.websocketserver.infrastructure.adapter.out.websocket;

import com.chat.websocketserver.domain.model.Mensaje;

/**
 * DTO del adaptador de salida: formato JSON que reciben los clientes suscritos.
 */
public record MensajeSalidaDto(String nombre, String contenido) {

    public static MensajeSalidaDto fromDomain(Mensaje mensaje) {
        return new MensajeSalidaDto(mensaje.nombre(), mensaje.contenido());
    }
}
