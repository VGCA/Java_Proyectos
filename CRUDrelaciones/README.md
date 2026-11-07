# Proyecto CRUD sobre relaciones

Arquitectura hexagonal (puertos y adaptadores), JDK 27, Spring Boot 4.1.2, sin Lombok.

```
domain/            modelo (records) y puertos (in = casos de uso, out = repositorios)
application/       servicios que implementan los casos de uso (Java puro)
infrastructure/    adaptadores: web (REST), persistence (JPA) y configuración de beans
```

Ejecutar tests: `./mvnw clean test`
