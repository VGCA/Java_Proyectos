package com.chat.websocketserver.application.port.in;

import com.chat.websocketserver.domain.model.Mensaje;

/**
 * Puerto de entrada (driving port): caso de uso "enviar un mensaje al chat".
 */
public interface EnviarMensajeUseCase {

    void enviar(Mensaje mensaje);
}
