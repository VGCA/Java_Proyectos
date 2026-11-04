# Quiz API (hexagonal architecture)

Requirements: JDK 27, Spring Boot 4.1.2.

1. Fill in the values in `docker-compose.yml` and run `docker-compose up -d`.
2. Export the DB password: `export mypassword=...` (Windows: `set mypassword=...`).
3. `./mvnw spring-boot:run`

Tests (no PostgreSQL needed, they use H2): `./mvnw clean test`

## Package layout

```
domain/                     pure model + domain exceptions (no frameworks)
application/port/in         use cases offered by the app   (driving ports)
application/port/out        what the app needs from outside (driven ports)
application/service         use case implementations (plain Java)
infrastructure/adapter/in/web              REST controllers + DTOs
infrastructure/adapter/out/persistence     JPA entities, Spring Data repos, adapters
infrastructure/config       BeanConfig: wires services as Spring beans
```
Dependency rule: `infrastructure -> application -> domain`, never the reverse.
