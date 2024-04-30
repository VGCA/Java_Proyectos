package com.testing.demo;

import com.testing.demo.model.Persona;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

public class Main {

    public static void main(String[] args) {

        Optional<String> optionalString = Optional.of("Vallejo");
        String other = optionalString.orElse("Other");
        System.out.println("Valor de other -> " + other);
        System.out.println("Valor del método genérico que devuelve cualquier cosa -> "
                + probando(List.of(2, 3, 4, 5)));
        Optional<String> changeOptional =
                optionalString.map(v -> "Lo cambio por esto");
        System.out.println("Rescato el valor de optionalString" +
                "y lo transformo en esto -> " + changeOptional.get());
        List<Integer> numerosLista = List.of(2, 3, 4, 5, 6, 7, 8);
        numerosLista.stream().mapToDouble(number -> number)
                .forEach(v -> System.out.println("Transformado en double " + v));
        List<String> famousNames = List.of("Pablo", "Juan", "Mateo", "Miqueas");
        List<Integer> collectPares = numerosLista.stream().filter(v -> v % 2 == 0)
                .toList();
        System.out.println("Números pares " + collectPares);
        System.out.println("Unión de valores = " + famousNames.stream().reduce((a, b) -> a + b).get());
        parseCsvFile();
    }

    /**
     * METHOD THAT RECEIVE ANYTHING AS PARAMETER
     * AND RETURN IT
     *
     * @param valor T generic
     * @param <T>   anything
     * @return parameter generic
     */
    private static <T> T probando(T valor) {
        return valor;
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
                System.out.println(persona);
            }
        } catch (IOException e) {
            System.out.println("Error en " + e.getMessage());
        }
    }
}
