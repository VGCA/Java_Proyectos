package com.bosonit.springmongo.controlador;

import java.util.Date;

import javax.json.Json;
import javax.json.JsonObject;
import javax.ws.rs.Consumes;
import javax.websocket.server.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.bosonit.springmongo.servicio.SeguridadServicio;

import com.bosonit.springmongo.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@RestController
@RequestMapping("/seguridad")
public class SeguridadController {

    private final SeguridadServicio seguridadServicio;
    private final Utils utils;



    public SeguridadController(SeguridadServicio seguridadServicio, Utils utils) {
        this.seguridadServicio = seguridadServicio;
        this.utils = utils;
    }

    @GetMapping
    public String saludo(){
        return "Todo funciona";
    }

    @PostMapping("/")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response validar(@PathParam("username") String username, @PathParam("password") String password) {
        boolean estado = seguridadServicio.validar(username, password);
        if (estado) {

            long tiempo = System.currentTimeMillis();
            String jwt = Jwts.builder()
                    .signWith(SignatureAlgorithm.HS256, utils.getKey())
                    .setSubject("Spring Security")
                    .setIssuedAt(new Date(tiempo))
                    .setExpiration(new Date(tiempo + 900000))
                    .claim("email", "vurzomikka@vusra.com")
                    .compact();
            JsonObject json = Json.createObjectBuilder().add("Token", jwt).build();

            return Response.status(Response.Status.CREATED).entity(json).build();
        }
        return Response.status(Response.Status.UNAUTHORIZED).build();
    }
}
