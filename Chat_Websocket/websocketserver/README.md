# Proyecto de websocket con servidor y cliente

Mejorado con SonarQube e implementación de test.

## Stack
- JDK 27
- Spring Boot 4.1.2
- Sin Lombok (se usan `record` y constructores explícitos)

## Arquitectura hexagonal

```
com.chat.websocketserver
├── domain.model                         Mensaje (modelo de dominio)
├── application
│   ├── port.in                          EnviarMensajeUseCase   (puerto de entrada)
│   ├── port.out                         PublicarMensajePort    (puerto de salida)
│   └── service                          EnviarMensajeService   (caso de uso)
└── infrastructure
    ├── adapter.in.websocket             MessageController + MensajeEntradaDto
    ├── adapter.out.websocket            WebSocketMensajePublisher + MensajeSalidaDto
    └── config                           WebSocketConfig, BeanConfiguration
```

Regla de dependencias: `infrastructure → application → domain`. El dominio y la
aplicación no importan nada de Spring.

## Ejecutar
```
./mvnw clean test
./mvnw spring-boot:run
```
El cliente (`../websocketcliente/index.html`) se conecta a `ws://localhost:8080/websocket`,
publica en `/app/envio` y se suscribe a `/tema/mensajes`.
