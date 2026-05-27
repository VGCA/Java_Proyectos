package com.bosonit.commandlinerunner;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import org.springframework.boot.CommandLineRunner;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.atLeastOnce;
import org.mockito.ArgumentCaptor;

import java.util.logging.Handler;
import java.util.logging.LogRecord;
import java.util.logging.Logger;

@SpringBootTest
class CommandLineRunnerApplicationTests {

	@Autowired
	private ApplicationContext context;


	@Test
	void saludoInicial_debeEjecutarseAlArrancarElContexto() {

		assertNotNull(context, "El contexto debe haberse iniciado correctamente");
	}

	@Test
	void saludoSecundario_debeExistirComoBeanEnElContexto() {

		CommandLineRunner runner = context.getBean("saludoSecundario", CommandLineRunner.class);
		assertNotNull(runner);
	}

	@Test
	void saludoSecundario_debeLoguearMensaje() throws Exception {
		CommandLineRunner runner = context.getBean("saludoSecundario", CommandLineRunner.class);

		Logger logger = Logger.getLogger(CommandLineRunnerApplication.class.getName());
		Handler mockHandler = mock(Handler.class);
		logger.addHandler(mockHandler);

		runner.run();

		ArgumentCaptor<LogRecord> captor = ArgumentCaptor.forClass(LogRecord.class);
		verify(mockHandler, atLeastOnce()).publish(captor.capture());

		boolean mensajeEncontrado = captor.getAllValues().stream()
				.anyMatch(r -> r.getMessage().contains("Hola desde la clase secundaria"));

		assertTrue(mensajeEncontrado, "Debería loguear el saludo secundario");

		logger.removeHandler(mockHandler);
	}


	@Test
	void ultimoSaludo_debeExistirComoBeanEnElContexto() {
		CommandLineRunner runner = context.getBean("ultimoSaludo", CommandLineRunner.class);
		assertNotNull(runner);
	}

	@Test
	void ultimoSaludo_debeEjecutarAmbosRunners() throws Exception {

		CommandLineRunner mockInicial     = mock(CommandLineRunner.class);
		CommandLineRunner mockSecundario  = mock(CommandLineRunner.class);

		CommandLineRunner ultimoSaludo = args -> {
			mockInicial.run(args);
			mockSecundario.run(args);
		};

		String[] args = {};
		ultimoSaludo.run(args);

		verify(mockInicial,    times(1)).run(args);
		verify(mockSecundario, times(1)).run(args);
	}
}
