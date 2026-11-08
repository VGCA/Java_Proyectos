# Proyecto reactive

Mejorado con SonarQube

## Arquitectura hexagonal (puertos y adaptadores)

```
com.monoflux.crudreactive
├── domain
│   ├── model        Car (record)
│   ├── exception    CarNotFoundException
│   └── port
│       ├── in       CarUseCase           (puerto de entrada)
│       └── out      CarRepositoryPort    (puerto de salida)
├── application
│   └── service      CarService           (implementa CarUseCase)
└── infrastructure
    └── adapter
        ├── in.web          CarController, CarDto, GlobalExceptionHandler
        └── out.memory      InMemoryCarRepository (implementa CarRepositoryPort)
```

Regla de dependencias: `infrastructure` -> `application` -> `domain`. El dominio no conoce Spring ni HTTP.

## Requisitos

- JDK 27
- Spring Boot 4.1.2 (Spring Framework 7), sin Lombok

## Endpoints

| Verbo  | Ruta           | Descripción                        |
|--------|----------------|------------------------------------|
| POST   | /save          | Crea un coche (201)                |
| GET    | /{id}          | Busca por id (404 si no existe)    |
| GET    | /name/{name}   | Busca por marca                    |
| GET    | /              | Lista todos (text/event-stream)    |
| PUT    | /update        | Actualiza (404 si no existe)       |
| DELETE | /delete/{id}   | Elimina                            |

## Tests

```
./mvnw clean test
```
