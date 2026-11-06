package com.portfolio.ecommerce;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Guardián de la arquitectura (sin librerías extra): lee el código fuente y comprueba la
 * regla de dependencias. El dominio y la aplicación NO pueden importar frameworks ni la
 * capa de infraestructura. Si alguien rompe la regla, el build falla.
 */
class ArquitecturaHexagonalTest {

    private static final Path BASE = Path.of("src/main/java/com/portfolio/ecommerce");

    private static final List<String> PROHIBIDOS_EN_NUCLEO = List.of(
            "import org.springframework", "import jakarta.", "import javax.", "import org.hibernate",
            "import lombok", "import " + "com.portfolio.ecommerce.infrastructure");

    @Test
    void dominioNoDependeDeNadaExterno() throws IOException {
        comprobar(BASE.resolve("domain"), PROHIBIDOS_EN_NUCLEO);
    }

    @Test
    void aplicacionNoDependeDeFrameworksNiDeInfraestructura() throws IOException {
        comprobar(BASE.resolve("application"), PROHIBIDOS_EN_NUCLEO);
    }

    @Test
    void elDominioNoImportaLaAplicacion() throws IOException {
        comprobar(BASE.resolve("domain"), List.of("import " + "com.portfolio.ecommerce.application"));
    }

    private void comprobar(Path carpeta, List<String> prohibidos) throws IOException {
        assertTrue(Files.isDirectory(carpeta), "No existe " + carpeta.toAbsolutePath());
        try (Stream<Path> ficheros = Files.walk(carpeta)) {
            for (Path f : ficheros.filter(p -> p.toString().endsWith(".java")).toList()) {
                String contenido = Files.readString(f);
                for (String p : prohibidos) {
                    assertTrue(!contenido.contains(p), f + " contiene la dependencia prohibida: " + p);
                }
            }
        }
    }
}
