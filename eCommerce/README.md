# Proyecto ecommerce — arquitectura hexagonal

Stack: Spring Boot 4.1.2 (Spring Framework 7, Spring Security 7, Hibernate 7), Thymeleaf, MySQL.
Se compila con `release 25` y se ejecuta en JDK 27. **Sin Lombok.**

## Estructura (puertos y adaptadores)

```
com.portfolio.ecommerce
├── domain                      NÚCLEO: reglas de negocio puras (Java sin frameworks)
│   ├── model                   Usuario, Producto, Orden, DetalleOrden, Carrito, ArchivoImagen
│   └── exception               RecursoNoEncontradoException, EmailYaRegistradoException
├── application                 NÚCLEO: casos de uso (Java sin frameworks)
│   ├── port.in                 PUERTOS DE ENTRADA  (qué ofrece la app): ProductoUseCase, UsuarioUseCase, OrdenUseCase
│   ├── port.out                PUERTOS DE SALIDA   (qué necesita la app): *RepositoryPort, ImagenStoragePort, CodificadorPasswordPort
│   └── service                 Implementación de los casos de uso
└── infrastructure              PERIFERIA: todo lo que depende de un framework
    ├── adapter.in.web          Controladores Thymeleaf, formularios, carrito de sesión
    ├── adapter.in.security     UserDetailsService, principal, handler de login
    ├── adapter.out.persistence Entidades JPA, repos Spring Data, mapper, adaptadores
    ├── adapter.out.storage     Imágenes en disco
    ├── adapter.out.security    BCrypt tras el puerto CodificadorPasswordPort
    └── config                  ApplicationConfig (composition root), SecurityConfig, WebConfig
```

**Regla de dependencias:** `infrastructure → application → domain`. Nunca al revés.
`ArquitecturaHexagonalTest` la comprueba en cada build leyendo los imports.

## Ejecutar

```
./mvnw clean test
./mvnw spring-boot:run
```
Necesita MySQL en `localhost:3306` con la base `ecommerce` (ver `application.properties`).

## Cambios respecto al proyecto original

- Lombok eliminado (getters/setters escritos a mano). Causa del `ExceptionInInitializerError`: Lombok 1.18.10
  no es compatible con los javac modernos.
- `javax.*` → `jakarta.*`; `WebSecurityConfigurerAdapter`/`antMatchers` → bean `SecurityFilterChain`.
- `mysql-connector-java` → `mysql-connector-j`; se quita `MySQL5Dialect` (ya no existe).
- El carrito era un campo del controlador (compartido por TODOS los usuarios): ahora es uno por sesión.
- El formulario de login enviaba `email` a `/usuario/acceder` pero Spring Security esperaba `username`:
  ahora `loginProcessingUrl("/usuario/acceder")` + `usernameParameter("email")`.
- Cerrar sesión ahora cierra la sesión de Spring Security (antes solo borraba un atributo).
- Un cliente solo puede ver el detalle de SUS órdenes (antes cualquier id).
- Número de orden: correlativo de 10 dígitos también a partir de la orden 1000 (antes devolvía "").
- Nombres de imagen saneados (evita path traversal con `../`).
- `/usuario/compras`, `/usuario/detalle/**`, `/order`, `/saveOrder` exigen estar autenticado.
- Email duplicado → 409 (antes provocaba error 500 en el login).

## Pendiente / decisiones abiertas

- CSRF sigue desactivado como en el original: con formularios `multipart` hay que configurar un
  `MultipartFilter` antes de activarlo.
- El número de orden se calcula leyendo la última orden (condición de carrera con compras simultáneas):
  siguiente paso, una secuencia o columna única en base de datos.
- Las plantillas Thymeleaf y los ficheros estáticos no se han modificado.
