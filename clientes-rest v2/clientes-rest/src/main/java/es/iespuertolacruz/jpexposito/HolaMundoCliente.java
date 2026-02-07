package es.iespuertolacruz.jpexposito;

import org.glassfish.jersey.jackson.JacksonFeature;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import java.util.logging.Logger;

public class HolaMundoCliente {

    public static void main(String[] args) {

        Logger logger = Logger.getLogger(HolaMundoCliente.class.getName());

        String url="http://localhost:8090/saludo";

        Client client = ClientBuilder.newBuilder()
                .register(JacksonFeature.class)
                .build();

        WebTarget target = client.target(url);
        String response = target.request().get().readEntity(String.class);
        logger.info(response);
    }
}

