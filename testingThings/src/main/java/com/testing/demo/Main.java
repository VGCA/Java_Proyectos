package com.testing.demo;

import com.testing.demo.model.Caja;
import com.testing.demo.model.CajaExtiende;
import com.testing.demo.model.Dog;
import com.testing.demo.model.Persona;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;
import java.util.logging.Logger;

public class Main {

    private static final Logger logger = Logger.getLogger(Main.class.getName());

    public static void main(String[] args) {



        Optional<String> optionalString = Optional.of("Vallejo");

        Optional<String> changeOptional =
                optionalString.map(v -> "Lo cambio por esto");
        logger.info("Rescato el valor de optionalString" +
                "y lo transformo en esto -> " + changeOptional.get());
        List<Integer> numerosLista = List.of(2, 3, 4, 5, 6, 7, 8);
        numerosLista.stream().mapToDouble(number -> number)
                .forEach(v -> logger.info("Transformado en double " + v));
        List<String> famousNames = List.of("Pablo", "Juan", "Mateo", "Miqueas");

        logger.info("Unión de valores = " + famousNames.stream().reduce((a, b) -> a + b).get());
        parseCsvFile();

        Caja<String, Integer> newCaja = new Caja<>("Hola", 2);
        logger.info("Los datos son " + newCaja.getDatos());
        logger.info("Las operaciones son " + newCaja.getOperaciones());
        newCaja.setDatos("Adiós");
        logger.info("Los nuevos datos son " + newCaja.getDatos());

        CajaExtiende cajaExtiende = new CajaExtiende<>();
        cajaExtiende.setDatos(new Dog(1, "Guau", "Tobby"));
        logger.info("La cantidad de patas de la clase" +
                "con extends Dog es " + cajaExtiende.howManyLegs());
    }


    /**
     * Method to parse a CSV file, save it in a class
     * and show it
     */
    private static void parseCsvFile() {
        Persona persona = new Persona();
        String line;
        String splitBy = ",";
        try {

            BufferedReader br = new BufferedReader(
                    new FileReader("src/main/resources/lista.csv"));
            while ((line = br.readLine()) != null) {
                String[] employee = line.split(splitBy);
                persona.setNombre(employee[0]);
                persona.setEdad(Integer.valueOf(employee[1]));
                persona.setPuesto(employee[2]);
            }
        } catch (IOException e) {
            logger.info("Error en " + e.getMessage());
        }
    }
}
