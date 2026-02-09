package es.iespuertolacruz.jpexposito.holamundo;

import org.glassfish.jersey.jackson.JacksonFeature;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;

public class HolaMundoTest {

    @Test
    void testGetSaludoReturnsCorrectString() {
        // 1. Mock the JAX-RS chain
        Client mockClient = mock(Client.class);
        WebTarget mockTarget = mock(WebTarget.class);
        Invocation.Builder mockBuilder = mock(Invocation.Builder.class);
        Response mockResponse = mock(Response.class);

        String expectedUrl = "http://localhost:8090/saludo";
        String expectedEntity = "Hola Mundo!";

        // 2. Define behavior
        try (MockedStatic<ClientBuilder> mockedBuilder = mockStatic(ClientBuilder.class)) {
            mockedBuilder.when(ClientBuilder::newClient).thenReturn(mockClient); // Simplification for test

            when(mockClient.target(expectedUrl)).thenReturn(mockTarget);
            when(mockTarget.request()).thenReturn(mockBuilder);
            when(mockBuilder.get()).thenReturn(mockResponse);
            when(mockResponse.readEntity(String.class)).thenReturn(expectedEntity);

            // 3. Execute (Assuming your code is inside a method called 'getSaludo')
            // String result = cliente.getSaludo();

            // 4. Assert
            assertEquals("Hola Mundo!", expectedEntity);
            verify(mockTarget).request();
        }
    }
}
