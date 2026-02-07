package com.chat.websocketserver;

import com.chat.websocketserver.controller.MessageController;
import com.chat.websocketserver.model.Mensaje;
import org.junit.jupiter.api.Test;

import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
class WebsocketserverApplicationTests {

	private final MessageController controller = new MessageController();

	@Test
	void shouldReturnMensajeWithSameData() {
		// GIVEN
		Mensaje input = new Mensaje("Juan", "Hola Mundo");

		// WHEN
		Mensaje result = controller.envio(input);

		// THEN
		assertThat(result).isNotNull();
		assertThat(result.nombre()).isEqualTo("Juan");
		assertThat(result.contenido()).isEqualTo("Hola Mundo");
	}

}
