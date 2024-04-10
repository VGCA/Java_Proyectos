package com.bosonit.SpringMongo.servicio;

import com.bosonit.SpringMongo.repositorio.RepoSeguridad;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SeguridadServicio {
    
    @Autowired
    RepoSeguridad repoSeguridad;

    /**
     * FUNCION QUE VALIDA UN USUARIO Y CONTRASENIA
     * @param username
     * @param password
     * @return true or false
     */
    public boolean validar(String username,String password){
        return repoSeguridad.validar(username, password);
    }
}
