package com.chat.websocketserver.application.port.out;

import com.chat.websocketserver.domain.model.Mensaje;

/**
 * Puerto de salida (driven port): difunde un mensaje a los clientes conectados.
 */
public interface PublicarMensajePort {

    void publicar(Mensaje mensaje);
}
