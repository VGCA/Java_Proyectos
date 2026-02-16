package com.bosonit.zipkincliente;

import com.bosonit.zipkincliente.controlador.WebController;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
class ZipkinclienteApplicationTests {

	@Mock
	private RestTemplate restTemplate;

	@InjectMocks
	private WebController myController; 

	@Test
	void testHelloWorld() {

		String mockServiceResponse = "Servicio Externo";
		when(restTemplate.getForObject("http://localhost:8080/saludo", String.class))
				.thenReturn(mockServiceResponse);

		String result = myController.helloWorld();

		String expectedHtml = "<h1>Respuesta desde Servicio Externo</h1>";
		assertEquals(expectedHtml, result);
	}

}
