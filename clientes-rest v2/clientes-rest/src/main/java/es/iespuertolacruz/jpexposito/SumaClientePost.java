package es.iespuertolacruz.jpexposito;

import org.glassfish.jersey.jackson.JacksonFeature;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import java.util.logging.Logger;

public class SumaClientePost {

    public static void main(String[] args) {

        Logger logger = Logger.getLogger(SumaClientePost.class.getName());


        String peticion = "http://localhost:8090/api/auth/";

        Client client = ClientBuilder.newBuilder()
                .register(JacksonFeature.class)
                .build();

        WebTarget target = client.target(peticion);
        String response = target.request().post(null, String.class);

        logger.info(response);

    }
}

