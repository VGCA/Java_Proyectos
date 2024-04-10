package com.bosonit.crudreactivo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories;

@SpringBootApplication
@EnableR2dbcRepositories
public class CrudreactivoApplication {

	public static void main(String[] args) {
		SpringApplication.run(CrudreactivoApplication.class, args);
	}

}
