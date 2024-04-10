package com.bosonit.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import java.io.*;

@SpringBootApplication
public class ErrorWarningApplication {

    public static void main(String[] args) throws IOException {
        Logger logger = LoggerFactory.getLogger(ErrorWarningApplication.class);
        logger.warn("Esto se verá por consola");

        String fileName = "misErrores.txt";
        String encoding = "UTF-8";

        try{
            PrintWriter writer = new PrintWriter(fileName, encoding);
            writer.println("Archivo de errores");
            writer.println("Mostrando los errores del fichero");
            writer.close();
        }
        catch (IOException e){
            System.out.println("Error al crear y escribir el archivo");
            e.printStackTrace();
        }

        FileReader fr = new FileReader(fileName);
        char [] a = new char[200];
        fr.read(a);

        for(char c : a)
            System.out.print(c);
        fr.close();

        SpringApplication.run(ErrorWarningApplication.class, args);
    }

}