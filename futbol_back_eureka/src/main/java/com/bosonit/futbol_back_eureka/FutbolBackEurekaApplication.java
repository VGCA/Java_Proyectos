package com.bosonit.futbol_back_eureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class FutbolBackEurekaApplication {

	public static void main(String[] args) {
		SpringApplication.run(FutbolBackEurekaApplication.class, args);
	}

}
