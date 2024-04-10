package com.bosonit.CommandLineRunner;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.converter.json.GsonBuilderUtils;

import javax.annotation.PostConstruct;

@SpringBootApplication
public class CommandLineRunnerApplication {

	public static void main(String[] args) {
		SpringApplication.run(CommandLineRunnerApplication.class, args);
	}

	@PostConstruct
	static void saludoInicial(){
		System.out.println("Hola desde la clase inicial");
	}

	@Bean
	static CommandLineRunner saludoSecundario(){
		return p->{
			System.out.println("Hola desde la clase secundaria");
		};
	}

	@Bean
	static CommandLineRunner ultimoSaludo(){
		return p->{
			saludoInicial();
			saludoSecundario();
		};
	}
}
