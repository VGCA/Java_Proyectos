package com.bosonit.springmongo.servicio;

import com.bosonit.springmongo.repositorio.RepoSeguridad;
import org.springframework.stereotype.Service;

@Service
public class SeguridadServicio {

    private final RepoSeguridad repoSeguridad;

    public SeguridadServicio(RepoSeguridad repoSeguridad) {
        this.repoSeguridad = repoSeguridad;
    }

    /**
     * FUNCION QUE VALIDA UN USUARIO Y CONTRASENIA
     *
     * @param username
     * @param password
     * @return true or false
     */
    public boolean validar(String username, String password) {
        return repoSeguridad.validar(username, password);
    }
}
