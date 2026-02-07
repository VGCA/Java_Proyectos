package com.chat.websocketserver.controller;

import com.chat.websocketserver.model.Mensaje;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class MessageController {

    @MessageMapping("/envio")
    @SendTo("/tema/mensajes")
    public Mensaje envio(Mensaje mensaje) {
        return new Mensaje(mensaje.nombre(), mensaje.contenido());
    }
}
