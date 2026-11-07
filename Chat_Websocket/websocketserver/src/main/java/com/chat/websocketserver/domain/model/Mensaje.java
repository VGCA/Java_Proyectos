package com.chat.websocketserver.domain.model;

/**
 * Modelo de dominio: un mensaje del chat. Sin dependencias de frameworks.
 */
public record Mensaje(String nombre, String contenido) {
}
