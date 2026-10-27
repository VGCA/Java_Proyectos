# Proyecto de chequeador de cabeceras usando Java y el protocolo HTTP

## Uso

```
mvn compile
java .\src\main\java\com\java\pruebas\adapter\in\cli\SecurityHeaderScanner.java https://www.example.com
```

## Arquitectura (hexagonal / puertos y adaptadores)

```
com.java.pruebas
├── domain/model                 <- núcleo puro: HeaderRule, HeaderFinding, Verdict, ResponseHeaders, ScanReport, SecurityHeaderRules
├── application
│   ├── port/in/ScanUrlUseCase   <- puerto de entrada (lo que la app ofrece)
│   ├── port/out/HeaderFetcher   <- puerto de salida (lo que la app necesita)
│   └── service/ScanService      <- implementa el caso de uso
└── adapter
    ├── in/cli                   <- SecurityHeaderScanner (main + composición) y ConsoleReportPrinter
    └── out/http                 <- JdkHttpHeaderFetcher (java.net.http)
```

Regla de dependencias: `adapter -> application -> domain`. Nunca al revés.
