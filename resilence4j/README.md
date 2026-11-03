# Resilience4j practice project (hexagonal architecture)

Run with `mvn spring-boot:run`, then call `curl http://localhost:8080/test`.

## Structure

```
com.sandbox.resilence4j
├── domain                      # business vocabulary, zero dependencies
│   ├── model/ServiceCallResult
│   └── exception/ServiceUnavailableException
├── application                 # use cases + ports, depends only on domain
│   ├── port/in/CallExternalServiceUseCase     (driving port)
│   ├── port/out/ExternalServicePort           (driven port)
│   └── service/CallExternalServiceService     (use-case implementation)
└── infrastructure              # everything framework/technology related
    ├── adapter/in/web/CircuitBreakerController      (HTTP -> use case)
    ├── adapter/out/external/SimulatedExternalServiceAdapter (@CircuitBreaker)
    └── config/UseCaseConfig                          (wiring)
```

Dependency rule: `infrastructure -> application -> domain`, never the reverse.
