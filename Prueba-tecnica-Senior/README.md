# Prueba técnica de trabajo

Revisada con SonarQube

## Arquitectura (hexagonal / ports & adapters)

```
domain/        -> modelo y excepciones de negocio (Java puro)
application/
  port/in      -> casos de uso que ofrece la app (interfaces)
  port/out     -> lo que la app necesita del exterior (interfaces)
  service      -> implementación de los casos de uso (Java puro)
adapter/
  in/web       -> REST (controllers + DTOs HTTP)
  in/kafka     -> consumidor Kafka
  out/persistence -> JPA/PostgreSQL
  out/kafka    -> productor Kafka
  messaging    -> formato del mensaje del topic
config/        -> composition root (une puertos con implementaciones)
```

Regla de dependencias: `adapter -> application -> domain`. Nunca al revés.
`domain` y `application` no importan Spring, JPA ni Kafka.
