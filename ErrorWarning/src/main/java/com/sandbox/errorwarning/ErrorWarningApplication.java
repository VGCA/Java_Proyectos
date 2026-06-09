package com.sandbox.errorwarning;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import java.io.*;
import java.nio.charset.StandardCharsets;

@SpringBootApplication
public class ErrorWarningApplication {

    public static void main(String[] args) {

        Logger logger = LoggerFactory.getLogger(ErrorWarningApplication.class);
        logger.warn("Esto se verá por consola");

        String fileName = "misErrores.txt";
        String encoding = StandardCharsets.UTF_8.name();

        try (PrintWriter writer = new PrintWriter(fileName, encoding)) {
            writer.println("Archivo de errores");
            writer.println("Mostrando los errores del fichero");
        } catch (IOException e) {
            logger.error("Error al crear y escribir el archivo: {}", e.getMessage());
        }

        try (FileReader fr = new FileReader(fileName, StandardCharsets.UTF_8)) {
            char[] buffer = new char[200];
            int charsRead = fr.read(buffer);
            if (charsRead > 0) {
                String content = new String(buffer, 0, charsRead); // Solo los chars válidos
                logger.info("Contenido del archivo:{}{}", System.lineSeparator(), content);
            }
        } catch (IOException e) {
            logger.error("Error al leer el archivo: {}", e.getMessage());
        }

        SpringApplication.run(ErrorWarningApplication.class, args);
    }

}