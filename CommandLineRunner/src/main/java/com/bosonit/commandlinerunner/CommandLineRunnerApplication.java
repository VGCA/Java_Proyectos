package com.bosonit.commandlinerunner;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import javax.annotation.PostConstruct;
import java.util.logging.Logger;

@SpringBootApplication
public class CommandLineRunnerApplication {

    static Logger logger = Logger.getLogger(CommandLineRunnerApplication.class.getName());

    public static void main(String[] args) {
        SpringApplication.run(CommandLineRunnerApplication.class, args);
    }

    @PostConstruct
    static void saludoInicial() {
        logger.info("Hola desde la clase inicial");
    }

    @Bean
    static CommandLineRunner saludoSecundario() {
        return args -> logger.info("Hola desde la clase secundaria");
    }

    @Bean
    static CommandLineRunner ultimoSaludo() {
        return p -> {
            saludoInicial();
            saludoSecundario();
        };
    }

    @Bean
    static CommandLineRunner ultimoSaludo(CommandLineRunner saludoInicial, CommandLineRunner saludoSecundario) {
        return args -> {
            saludoInicial.run(args);
            saludoSecundario.run(args);
        };
    }
}
