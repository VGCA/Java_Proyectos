## Resumen

Proyectos utilizados para explicar la configuración del servidor de registro y descubrimiento Eureka

## ServicioClientes

Proyecto SpringBoot que publica un servicio REST en el que los recursos son los Clientes.

El servicio escucha en el puerto 8090 y el api es:

* get clientes
* get clientes/{login}
* post clientes
* put clientes/{login}
* delete clientes/{login}

Esta aplicación utiliza una base de datos H2. El el fichero de configuración se indica que está en c:/H2

## Servicio pedidos

Proyecto SpringBoot que publica un servicio REST en el que los recursos son los Pedidos

Escucha en el 8100 de localhost y el api es:

* get pedidos
* get pedidos/{id}
* post pedidos
* put pedidos/{id} (no implementado en la lógica de negocio)
* delete pedidos/{id} (nom implementado en la lógica de negocio)

Esta aplicación utiliza una base de datos H2. El el fichero de configuración se indica que está en c:/H2

##EurekaServer

Proyecto SpringBoot que levanta el servidor Eureka en el puerto 8084 de localhost

##Instrucciones para arrancar las aplicaciones

* Colocar las bases de datos en C:/H2 (si se colocan en otro sitio modificar los ficheros application.properties)
* Ejecutar los main (están en el paquete com.curso de los tres proyectos)
* Enviar una petición POST a localhost:8100 con content-type 'application/json' y el siguiente documento en el body:

{
	"codigo"  : "PED-0",
	"fecha"   : "Hoy",
	"estado"  : "Pendiente",
	"cliente" : {
		"login" : "monty@hotmail.com"
	}
}

## Arquitectura (hexagonal)

ServicioClientes y ServicioPedidos estan organizados en puertos y adaptadores. Las dependencias apuntan siempre hacia el centro:

* dominio: modelo (records inmutables) y excepciones. Sin frameworks.
* aplicacion: puertos de entrada (casos de uso), puertos de salida (lo que necesita el nucleo) y servicios que implementan los casos de uso.
* infraestructura: adaptadores REST (entrada), adaptadores de persistencia JPA y cliente Feign (salida) y la configuracion que ensambla los beans.

Las entidades JPA viven solo en infraestructura/adaptador/salida/persistencia y mantienen los mismos nombres de tabla y columna que antes.

## Versiones

* JDK 27 (java.version=27)
* Spring Boot 4.1.2 y Spring Cloud 2025.1.4 (Boot 4.1 declara soporte oficial hasta JDK 26; si al arrancar con JDK 27 aparece "Unsupported class file major version 71", compilar con maven.compiler.release=26 o esperar a Spring Boot 4.2)
* Maven wrapper 3.9.11
* Sin Lombok (el proyecto original no lo usaba)

## Aviso sobre las bases de datos H2

Spring Boot 4 trae H2 2.x, cuyo formato de fichero no es compatible con el de H2 1.4.200 que traia Spring Boot 2.3. Si ya tienes ficheros en C:/H2 creados con la version anterior, borralos (se regeneran solos) o exportalos/importalos con SCRIPT/RUNSCRIPT.

## Tests

* mvn clean test en cada modulo (los tests de contexto usan H2 en memoria y Eureka desactivado, ver src/test/resources/application.properties).
